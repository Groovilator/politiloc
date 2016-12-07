package politiloc;

public class County {

    private final String id;
    private final String name;
    private final String state;

    private final int demvotes;
    private final int gopvotes;
    private final int totalvotes;
    private final int diff;
    private final double perdem;
    private final double pergop;

    public County(String id, String name, String state, int demvotes, int gopvotes, int totalvotes, int diff, double perdem, double pergop) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.demvotes = demvotes;
        this.gopvotes = gopvotes;
        this.totalvotes = totalvotes;
        this.diff = diff;
        this.perdem = perdem;
        this.pergop = pergop;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public int getDemvotes() {
        return demvotes;
    }

    public int getGopvotes() {
        return gopvotes;
    }

    public int getTotalvotes(){
        return totalvotes;
    }

    public int getDiff(){
        return diff;
    }

    public double getPerdem(){
        return perdem;
    }

    public double getPergop(){
        return pergop;
    }
}