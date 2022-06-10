<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<script type="text/javascript"
    src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
    </script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Login Page </title>
<style>
Body {
font-family: Calibri, Helvetica, sans-serif;
background-color: pink;
}
button {
background-color: #4CAF50;
width: 100%;
color: orange;
padding: 15px;
margin: 10px 0px;
border: none;
cursor: pointer;
 }
form {
border: 3px solid #f1f1f1;
}
input[type=text], input[type=password] {
width: 100%;
margin: 8px 0;
padding: 12px 20px;
display: inline-block;
border: 2px solid green;
box-sizing: border-box;
}
button:hover {
opacity: 0.7;
}
.cancelbtn {
width: auto;
padding: 10px 18px;
margin: 10px 5px;
}


.container {
padding: 25px;
background-color: lightblue;
}
</style>
</head>
<body>
<center> <h1> Welcome To Remote Help Desk </h1> </center>
<form>
<div class="container">
    <label>Username : </label>
    <input type="text" id="userName" placeholder="Enter Username" name="userName" required>
    <label>Password : </label>
    <input type="password" id="password" placeholder="Enter Password" name="password" required>

    <input type="submit"  onclick="return submitForm(this, event);">

</div>
</form>

<script>

function submitForm(thisObj, thisEvent) {
var password= document.getElementById("password").value;                 // password is an input field

var userName= document.getElementById("userName").value;                      // userName is an input field
console.log("hello");

var jsonDataObject = new Object();
jsonDataObject.password = password;
jsonDataObject.username = userName;


console.log(jsonDataObject);
// turn the jsonData object into a string so it can be passed to the servlet
var jsonData = JSON.stringify(jsonDataObject);
console.log(jsonData);
$.ajax({type:"POST",url:"http://localhost:8080/helpDesk/auth",data: jsonData, dataType:"json"}, function(data) {
alert(data.message);
('#return_message').html(data.message);
});

return false;  // prevents the page from refreshing before JSON is read from server response
}
</script>

</body>
</html>