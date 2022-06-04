<!-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Document</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body> -->

<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<h1>${head}</h1>
	<form:form method="post" modelAttribute="todo">
		<form:hidden path="id" />
		<fieldset class="form-group">
			<form:label path="desc">Description :</form:label>

			<form:input path="desc" type="text" class="form-control"
				required="required" title="Enter at least 10 Characters..." />
			<form:errors path="desc" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="targetDate">Target Date :</form:label>

			<form:input path="targetDate" type="text" class="form-control"
				required="required" title="Enter at least 10 Characters..." />
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>
		<button type="submit" class="btn btn-success">ADD</button>
	</form:form>

</div>

<%@ include file="common/footer.jspf"%>

<!-- <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script
		src="webjars/bootstrap-datepicker/1.0.1/js/bootstrap-datepicker.js"></script>

	<script>
		$('#targetDate').datepicker({
			format : 'dd/mm/yyyy'
		});
	</script>

</body>
</html> -->