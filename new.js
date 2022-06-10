window.onload = function () {
    document.getElementById("getid-btn").addEventListener("click", getid);
    document.getElementById("getall-btn").addEventListener("click", getall);
  }
  function getid() {

      // Convenience references
      let idInput = document.getElementById('userid');
      let usernameInput = document.getElementById('username');

      let userID = idInput.value;
      let username = usernameInput.value;
console.log(userID)

          if (userID) {

              window.location.href = `http://localhost:8080/helpDesk/id?id=${userID}`;

              }
          if (username) {

              window.location.href = `http://localhost:8080/helpDesk/id?username=${username}`;



    }


  }
    function getall() {

    let respData = fetch("/helpDesk/users", {
        method: "GET",
        headers: {
                'Content-Type': 'application/json'
                    }

    }).then(resp => resp.json()).then(data => {console.log(data);});

//                  return resp.json();


              }

              console.log(respData);


                       JSONToHTMLTable(respData, "tblEmployee");

                      function JSONToHTMLTable(jsonData, elementToBind) {

                          //This Code gets all columns for header   and stored in array col
                          var col = [];
                          for (var i = 0; i < jsonData.length; i++) {
                              for (var key in jsonData[i]) {
                                  if (col.indexOf(key) === -1) {
                                      col.push(key);
                                  }
                              }
                          }

                          //This Code creates HTML table
                          var table = document.createElement("table");

                          //This Code getsrows for header creader above.
                          var tr = table.insertRow(-1);

                          for (var i = 0; i < col.length; i++) {
                              var th = document.createElement("th");
                              th.innerHTML = col[i];
                              tr.appendChild(th);
                          }

                          //This Code adds data to table as rows
                          for (var i = 0; i < jsonData.length; i++) {

                              tr = table.insertRow(-1);

                              for (var j = 0; j < col.length; j++) {
                                  var tabCell = tr.insertCell(-1);
                                  tabCell.innerHTML = jsonData[i][col[j]];
                              }
                          }

                          //This Code gets the all columns for header
                          var divContainer = document.getElementById(elementToBind);
                          divContainer.innerHTML = "";
                          divContainer.appendChild(table);
                      }


















