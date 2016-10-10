<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="WEB-INF/showErrorMessage.jsp" %>

<html>

<head>  
  <link rel="stylesheet" type="text/css" href="default.css">

  <script language="JavaScript">
      function checkAll(field)
      {
          for (i=0; i < field.length; i++)
          {
              field[i].checked = true;
          }
      }
  </script>
</head>

<body>
  <p><a href="controller?action=addEmp">[Add Employee]</a></p>
  
  <form name="deleteForm" method="post" action="controller">
  <input type="hidden" name="action" value="deleteCar" />
  <table>
      <th><a href="javascript:checkAll(document.deleteForm.id)">Select All</a></th>
      
      <th> Action</th>
      <th> Email </th>
      <th> Phone </th>
      <th> Date of Birth </th>
      <th> Date of Joining </th>
      <th> Address </th>
      <th> Department </th>
      <th> Project </th>
      <th> Role </th>
    </tr>
      <td><input type="checkbox" name="id" value="${emp.id}"></td>
      <td><a href="controller?action=editemp&id=${emp.id}">Edit</a></td>
      <td>${emp.phone}</td>
      <td>${emp.dob}</td>
      <td>${emp.doj}</td>
      <td>${emp.address}</td>
      <td>${emp.department}</td>
      <td>${emp.project}</td>
      <td>${emp.role}</td>
    
    <tr>
      <td colspan="11">
        <input type="submit" name="Delete Checked" value="Delete Checked" />
        &nbsp;&nbsp;
        <input type="reset" name="Reset" value="Reset" />
      </td>
    </tr>
    
  </form>

</html>