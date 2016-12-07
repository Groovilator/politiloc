package politiloc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class PolitiLocController {
    @RequestMapping("/politiloc")
    public County politiloc(@RequestParam(value="lat", defaultValue="39.774769") float lat, @RequestParam(value="lon", defaultValue="-104.985352") float lon) {
		Connection c = null;
		Statement stmt = null;
		String county_id = "Not Found";
		String county_name = null;
	    String state_abbr = null;

	    int votes_dem = -1;
	    int votes_gop = -1;
	    int total_votes = -1;
	    int diff = -1;
	    double per_dem = -1;
	    double per_gop = -1;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://politiloc.c5ja9eqpu7xn.us-west-2.rds.amazonaws.com:5432/politiloc", "politiloc", "heybaby1");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String query_str = "SELECT counties.geoid, results.votes_dem, results.votes_gop, results.total_votes, results.per_dem, results.per_gop, results.diff, results.per_point_diff, results.state_abbr, results.county_name FROM counties JOIN results ON counties.geoid=results.geoid WHERE ST_Contains(geom, ST_GeomFromText('Point(" + Float.toString(lon) + " " + Float.toString(lat) + ")', 4269))";
			System.console().writer().println(query_str);
			ResultSet rs = stmt.executeQuery(query_str);
			if(rs.next()){
				county_id = rs.getString("geoid");
				county_name = rs.getString("county_name");
				state_abbr = rs.getString("state_abbr");

				votes_dem = rs.getInt("votes_dem");
				votes_gop = rs.getInt("votes_gop");
				total_votes = rs.getInt("total_votes");
				diff = rs.getInt("diff");
				per_dem = rs.getDouble("per_dem");
				per_gop = rs.getDouble("per_gop");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
        return new County(county_id, county_name, state_abbr, votes_dem, votes_gop, total_votes, diff, per_dem, per_gop);
    }
}