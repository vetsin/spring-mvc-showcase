<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<c:if test="${!ajaxRequest}">
<html>
<head>
	<title>forms | mvc-showcase</title>
	<link href="<c:url value="/resources/form.css" />" rel="stylesheet"  type="text/css" />		
	<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
</head>
<body>
</c:if>
	<div id="formsContent">
		<h2>Forms</h2>
		<p>
			See the <code>org.springframework.samples.mvc.form.security</code> package for the @Controller code	
		</p>
		
		<c:if test="${bean != null}">
<%-- 			<c:if test="${bean.privateBean.hash != null}"> --%>
<!-- 				<div id="assignment" class="error">Mass Assignment!</div> -->
<%-- 			</c:if> --%>
			bean: ${bean}
			pbean: ${bean.privateBean}
			hpbean: ${bean.privateBean.hash}
		</c:if>

		<c:url var="secureAction" value="/form/assignment/secure" />
		<form:form id="form" method="post" modelAttribute="secureBean" action="${secureAction}" cssClass="cleanform">
			<div class="header">
		  		<h2>Form</h2>
		  		<c:if test="${not empty message}">
					<div id="message" class="success">${message}</div>	
		  		</c:if>
		  		<s:bind path="*">
		  			<c:if test="${status.error}">
				  		<div id="message" class="error">Form has errors</div>
		  			</c:if>
		  		</s:bind>
			</div>
		  	<fieldset>
		  		<legend>Personal Info</legend>
		  		<form:label path="name">
		  			Name <form:errors path="name" cssClass="error" />
		 		</form:label>
		  		<form:input path="name" />
	
		  	</fieldset>
		  	
		  	<fieldset>
		  		<legend>Restricted Info</legend>
		  		<form:label path="privateBean.hash">
		  			Hash <form:errors path="privateBean.hash" cssClass="error" />
		 		</form:label>
		  		<form:input path="privateBean.hash" />
		  	</fieldset>
	
			<p><button type="submit">Submit Valid Content</button></p>
		</form:form>
		
		<c:url var="insecureAction" value="/form/assignment/insecure" />
		<form:form id="form" method="post" modelAttribute="insecureBean" action="${insecureAction}" cssClass="cleanform">

		  	<fieldset>
		  		<legend>Personal Info</legend>
		  		<form:label path="name">
		  			Name <form:errors path="name" cssClass="error" />
		 		</form:label>
		  		<form:input path="name" />
		  	</fieldset>
		  	
		  	<fieldset>
		  		<legend>Restricted Info</legend>
		  		<form:label path="privateBean.hash">
		  			Hash <form:errors path="privateBean.hash" cssClass="error" />
		 		</form:label>
		  		<form:input path="privateBean.hash" />
		  	</fieldset>
	
			<p><button type="submit">Submit Invalid Content</button></p>
		</form:form>
		
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("#form").submit(function() {  
					$.post($(this).attr("action"), $(this).serialize(), function(html) {
						$("#formsContent").replaceWith(html);
						$('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
					});
					return false;  
				});			
			});
		</script>
	</div>
<c:if test="${!ajaxRequest}">
</body>
</html>
</c:if>