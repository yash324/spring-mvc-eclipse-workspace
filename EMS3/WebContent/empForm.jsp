<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  <!--input type="hidden" name="action" value="saveEmp" /-->
  <input type="hidden" name="id" value="${emp.id}" />

  <table>
    <!-- input fields -->
      <td><input type="text" name="name"  value="${emp.name}" /></td>
    </tr>  
    <tr>  
      <td><input type="text" name="email" value="${emp.email}" /></td>
    </tr>
    <tr>
      <td><input type="text" name="phone" value="${emp.phone}" /></td>
    </tr>
	<tr>
    <!-- Save/Reset buttons -->
    <tr>
      <td colspan="2">
        <input type="submit" name="save" value="Save" /> 
        &nbsp;&nbsp;
        <input type="reset" name="reset" value="Reset" />
      </td>
    </tr>                
  </form>