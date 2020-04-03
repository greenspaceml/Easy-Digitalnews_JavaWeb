<%@page import="java.util.ArrayList"%>
<%@page import="model.News"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Right</title>
        <link href="css/sidebar.css" rel="stylesheet" type="text/css" />
        
        <%
            News top1 = (News) request.getAttribute("top1");
            ArrayList<News> top5 = (ArrayList<News>) request.getAttribute("top5");
        %>
    </head>
    <body>
        <div class="sidebar">
            
            <div class="news">
                <div class="tittle">
                    <div>Digital News</div>
                </div>
                <div class="text">
                    <%=top1.getShortDes()%>
                </div>
            </div>
                
            <div class="newst">
                <div class="tittle">
                    Search
                </div>
                <form action="search" method="post">
                    <input class="searchBox" type="text" name="searchedText" size="15"  required>
                    <input class="searchButton" type="submit" name="btnGo" value="Go">
                </form>
            </div>
                
            <div class="newst">
                <div class="tittle">
                    <div>Last Articles</div><br>
                </div>
                <%for (News elem : top5) {%>
                <div class="lastArticles">
                    <a href="detail?id=<%=elem.getId()%>"><%=elem.getTitle()%></a>
                </div>
                <%}%>
            </div>
            
        </div>
    </body>
</html>
