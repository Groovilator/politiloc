$(document).ready(function () {
  $('#locateButton').click(function() {
    $.ajax({
      url: '/politiloc',
      type: 'GET',
      data: getRequestData(),
      error: handleError,
      success: handleSuccess
    });
  });
})

function getRequestData () {
  return {
    lat: document.getElementById('lat').value,
    lon: document.getElementById('lon').value
  }
}

// The server could 404 when no data instead of returning Not Found in the ID
function handleSuccess (data, status) {
  //You can use any jQuery/JavaScript here!!!
  console.log(data)
  $("#results").text(JSON.stringify(data))
}

function handleError (xhr, options, err) {

}