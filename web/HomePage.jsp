<%@page import="context.ContextPath"%>
<%@page import="model.News"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <%
            News top1 = (News) request.getAttribute("top1");
            String imgPath = (String) request.getAttribute("imgPath");
        %>
    </head>
    <body> 
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                    <div class="news">
                        <div class="tittle">
                            <%=top1.getTitle()%>
                        </div>
                        <div class="image">
                            <img src="<%=imgPath%>"/>
                        </div>
                        <div class="text">
                            <%=top1.getDescription()%>
                        </div>
                        <div class="signature">
                            <div class="icon1"></div>
                            <div class="icon2"></div>
                            By <%=top1.getAuthor()%> | <%=top1.getDateFormat()%>
                        </div>
                    </div>
                </div>
                <jsp:include page="Sidebar.jsp"/> 
            </div>
<div class="footer"></div>
        </div>
    </body>
</html>
