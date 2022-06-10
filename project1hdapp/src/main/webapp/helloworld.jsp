       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
           pageEncoding="ISO-8859-1"%>
       <html>
       <head>
           <title>Dynamic Drop Down List Demo - CodeJava.net</title>
       </head>
       <body>

       <div align="center">
           <h2>Dynamic Drop Down List Demo</h2>
           <form action="users" method="post">
               Select a Category:&nbsp;
               <select name="app_users">
                   <c:forEach items="${users}" var="app_users">
                       <option value="app_users.userId}"
                           <c:if test="${app_users.userId eq selectedUserId}">selected="selected"</c:if>
                           >
                           ${app_users.lastName}
                       </option>
                   </c:forEach>
               </select>
               <br/><br/>
               <input type="submit" value="Submit" />
           </form>
       </div>
       </body>
       </html>
