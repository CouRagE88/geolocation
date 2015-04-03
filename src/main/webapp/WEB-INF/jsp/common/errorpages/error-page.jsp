<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Oh snap!</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/pages/error-pages.css" />" />
</head>

<%-- a dedicated message bundle is used here, as this page deliberately does not rely on Spring i18n handling --%>
<fmt:setBundle basename="lang.errorpages" var="msg" />

<body>
	<div class="baseline">

		<!-- Declare a variable whose value is retrieved from the model.
			 The thrown exception is set to the model by a simpleMappingExceptionResolver bean -->
		<c:set var="exception" scope="session" value="${ex}"/>
		
		<!-- Choose the appropriate error message based on all the commotion. -->
		<c:choose>
			<c:when test="${exception eq 'com.epam.bench.geolocation.service.InvalidIpAddressException'}">
				<div class="errorPane">
					<h1>
						<fmt:message key="errorpage.InvalidIpAddress.title" bundle="${msg}" />
					</h1>
					<h2>
						<fmt:message key="errorpage.InvalidIpAddress.description" bundle="${msg}" />
					</h2>
					<p>
						<fmt:message key="errorpage.annex" bundle="${msg}">
							<fmt:param>
								<a href="<c:url value="/ipsearh.html" />">
							</fmt:param>
							<fmt:param>
								</a>
							</fmt:param>
						</fmt:message>
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${exception eq 'com.epam.bench.geolocation.service.LocationDoesNotExistException'}">
						<div class="errorPane">
							<h1>
								<fmt:message key="errorpage.LocationDoesNotExist.title" bundle="${msg}" />
							</h1>
							<h2>
								<fmt:message key="errorpage.LocationDoesNotExist.description" bundle="${msg}" />
							</h2>
							<p>
								<fmt:message key="errorpage.annex" bundle="${msg}">
									<fmt:param>
										<a href="<c:url value="/ipsearch.html" />">
									</fmt:param>
									<fmt:param>
										</a>
									</fmt:param>
								</fmt:message>
							</p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="errorPane">
							<h1>
								<fmt:message key="errorpage.unknown.title" bundle="${msg}" />
							</h1>
							<h2>
								<fmt:message key="errorpage.unknown.description" bundle="${msg}" />
							</h2>
						</div>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
