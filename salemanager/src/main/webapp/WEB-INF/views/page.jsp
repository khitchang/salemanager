<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>Sale Manager - ${title}</title>
<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">


<!-- Add Theme CSS here -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">


<!-- Add custom CSS here -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="se-pre-con"></div>
	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>


		<!-- Page Content -->

		<div class="content">
			<c:if test="${userClickHome == true}">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- load when user clicks about  -->
			<c:if test="${userClickAbout == true}">
				<%@include file="about.jsp"%>
			</c:if>


			<!-- load when user clicks contact -->
			<c:if test="${userClickContact == true}">
				<%@include file="contact.jsp"%>
			</c:if>

			<!-- Page Content -->
			<!-- loading the home content -->
			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true }">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- single Product Content -->
			<!-- loading the home content -->
			<c:if test="${userClickShowProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>


			<!-- load when user clicks manage products -->
			<c:if test="${userClickManageProducts == true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>

			<!-- load when user clicks manage products -->
			<c:if test="${userClickShowCart  == true}">
				<%@include file="cart.jsp"%>
			</c:if>



		</div>
		<!-- /.footer come here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Jquery -->
		<script src="${js}/jquery.js"></script>

		<!-- Jquery -->
		<script src="${js}/jquery.validate.js"></script>

		<!-- Bootstrap core javasrcipt -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- Jquery DataTables plugin -->
		<script src="${js}/jquery.dataTables.js"></script>


		<!-- Jquery DataTables bootstrap plugin -->
		<script src="${js}/dataTables.bootstrap.js"></script>

		<!-- Bootbox Js plugin -->
		<script src="${js}/bootbox.min.js"></script>

		<!-- JavaScript -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>
