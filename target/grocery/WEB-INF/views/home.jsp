<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<spring:url value="/js/jQuery/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/js/home.js" />"></script>
<html>
<script type="text/javascript">
   var fileError = '${fileError}';
   var contextPath = '${pageContext.request.contextPath}';
</script>
<body>
<h2>Welcome to Grocery Store</h2>

<h2>Please select a JSON file for Uploading</h2>
	<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/fruit-stock/uploadJSON">
	  <div id="errorMsgs" style="display:none">
	  </div>
		Upload File: <input type="file" name="file">
		<br/><br/><input type="submit" value="Upload"> 
	</form>
	
 </body>
</html>
