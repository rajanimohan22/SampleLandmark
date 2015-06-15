<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<spring:url value="/js/jQuery/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/js/fruitDetail.js" />"></script>
<html>
<script type="text/javascript">
   var fileError = '${fileError}';
   var contextPath = '${pageContext.request.contextPath}';
</script>
<body>
<form method="post" id="fruitDetailForm" action="${pageContext.request.contextPath}/priceAndFilter/displayAllFruits">
<h2>Grocery Store</h2>
    <c:if test="${empty fruitList}">
      There are no items in the Database. Please upload a JSON file by clicking <a href="${pageContext.request.contextPath}/">here</a>
    </c:if>
	<c:if test="${not empty fruitList}">
	   <table width="60%">
	     <tr>
	      <td>
	      	<div>
	      		Please enter text to filter fruits By Name:
	      		<input type="text" id="filterBox" maxlength="10" size="10"/>
	   	    	<input type="button" id="filterButton" onclick="doFilter();" value="Filter Fruits"/>
	   	    	<input type="button" id="clearFilterButton" onclick="clearFilterDisplayAll();" value="Clear Filter"/>
	   	    </div>
	      </td>
	     </tr>
	   </table>
	   </br>
	   <div id="fruitTableContents">
 			<table border=1 width="50%">
		      <tr>
		            <td><b>Name</b></td>
		            <td><b>Price</b></td>
		            <td><b>Stock</b></td>
		            <td><b>Updated Date</b></td>
		      </tr>
		       <c:forEach var="fruitItem" items="${fruitList}">
				 <tr id="${fruitItem.id}">
				    <td>${fruitItem.name}</td>
				    <td id="${fruitItem.price}"><input type="text" id="${fruitItem.id}-input" onfocus="enableButton(this);" onblur="enableDisbaleButton(this)" value="${fruitItem.price}" maxlength="4" size="4" />
				    	<input id="${fruitItem.id}-button" type="button" value="Update Price" disabled="disabled" onclick="updatePrice(this)">
				    </td>
				    <td>${fruitItem.stock}</td>
				    <td>${fruitItem.updated}</td>
				 </tr>
			    </c:forEach>
			</table>
		</div>
	</c:if>
  </form>
</body>
</html>
