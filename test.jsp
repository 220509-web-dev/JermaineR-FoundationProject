<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
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
    <input type="text" placeholder="Enter Username" name= $(#userName) required>
    <label>Password : </label>
    <input type="password" placeholder="Enter Password" name="password" required>
    <button type="submit">Login</button>
    <input type="submit" name="submit" value="Submit" onclick="return submitForm(this, event);">
    <input type="checkbox" checked="checked"> Remember me
    <button type="button" class="cancelbtn"> Cancel</button>
    Forgot <a href="#"> password? </a>
</div>
</form>

<script>
function submitForm(thisObj, thisEvent) {
var userName= $('#userName').val();                      // userName is an input field
var password= $('#password').val();                      // password is an input field


var jsonDataObject = new Object();
jsonDataObject.userName = userName;
jsonDataObject.password = password;


// turn the jsonData object into a string so it can be passed to the servlet
var jsonData = JSON.stringify(jsonDataObject);

$.getJSON("exportservlet", {action:"export",json:jsonData}, function(data) {
alert(data.message);
$('#return_message').html(data.message);
});

return false;  // prevents the page from refreshing before JSON is read from server response
}
</script>
</body>
</html>



function submitForm(thisObj, thisEvent) {
var userName= $('#email').val();                      // userName is an input field
var password= $('#email').val();                      // password is an input field


var jsonDataObject = new Object();
jsonDataObject.userName = userName;
jsonDataObject.password = password;


// turn the jsonData object into a string so it can be passed to the servlet
var jsonData = JSON.stringify(jsonDataObject);

$.getJSON("exportservlet", {action:"export",json:jsonData}, function(data) {
alert(data.message);
$('#return_message').html(data.message);
});

return false;  // prevents the page from refreshing before JSON is read from server response
}



