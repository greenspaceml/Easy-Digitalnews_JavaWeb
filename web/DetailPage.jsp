<%@page import="context.ContextPath"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.News"%>
<%@page import="javax.naming.Context"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <%
            News detailNews = (News) request.getAttribute("detailNews");

            String errReporter = (String) request.getAttribute("errReporter");

            String imgPath = (String) request.getAttribute("imgPath");

        %>
    </head>
    <body> 
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                    <%
                        if (errReporter == null) {
                    %>
                    <div class="news">
                        <div class="tittle">
                            <%=detailNews.getTitle()%>
                        </div>
                        <div class="image">
                            <img src="<%=imgPath%>"/>
                        </div>
                        <div class="text">
                            <%=detailNews.getDescription()%>
                        </div>
                        <div class="signature">
                            <div class="icon1"></div>
                            <div class="icon2"></div>
                            By <%=detailNews.getAuthor()%> | <%=detailNews.getDateFormat()%>
                        </div>
                    </div>
                    <%} else {
                    %>
                    <h1><%=errReporter%></h1>
                    <%}%>
                </div>
                <jsp:include page="Sidebar.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
