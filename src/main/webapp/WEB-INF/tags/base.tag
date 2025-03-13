<%@ tag description="Base template" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="css" required="false" %>
<%@ attribute name="js" required="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} - SkyWay Airlines</title>
    
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- Base CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    
    <!-- Page specific CSS -->
    <c:if test="${not empty css}">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/${css}">
    </c:if>
</head>
<body>
    <jsp:include page="/WEB-INF/views/templates/header.jsp" />
    
    <main class="container-fluid py-4">
        <jsp:doBody />
    </main>
    
    <jsp:include page="/WEB-INF/views/templates/footer.jsp" />
    
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Page specific JavaScript -->
    <c:if test="${not empty js}">
        <script src="${pageContext.request.contextPath}/static/js/${js}"></script>
    </c:if>
</body>
</html>