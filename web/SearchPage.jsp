<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="context.ContextPath"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.News"%>
<%@page import="javax.naming.Context"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link href="css/search.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <%

            ArrayList<News> SearchedList = (ArrayList<News>) request.getAttribute("SearchedList");
            //ArrayList<String> listImagePath = (ArrayList<String>) request.getAttribute("listImagePath");
            String searchedText = (String) request.getAttribute("searchedText");
            int curentPage = (Integer) request.getAttribute("curentPage");
            int NumOfPage = (Integer) request.getAttribute("NumOfPage");
            //String imgPath = (String) request.getAttribute("imgPath");
            String errReporter = (String) request.getAttribute("errReporter");
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
                    <%
                        for (News news : SearchedList) {
                    %>
                    <div class="news">
                        <div class="tittle">
                            <a href="detail?id=<%=news.getId()%>">
                                <%=news.getTitle()%>
                            </a>
                        </div>
                            
                        <div class="image_search">
                            <img src="<%=news.getfullPath()%>"/>
                        </div>

                        <div class="text">
                            <%=news.getShortDes()%>
                        </div>

                        <br>
                    </div>
                    <%}%>
                    <div class="paging">
                        <%if (NumOfPage == 0) {
                        %>

                        <h3>Not found</h3>

                        <%} else {
                        %>

                        <% for (int i = 1; i <= NumOfPage; i++) {
                                if (i == curentPage) {
                        %>
                        <a class="curent"><%=i%></a>

                        <%} else {
                        %>
                        <a href="search?pageIndex=<%=i%>&searchedText=<%=searchedText%>" ><%=i%></a>  
                        <%}%>
                        <%}%>

                        <%}%>
                    </div>
                    <%} else {%>
                    <h1><%=errReporter%></h1>
                    <%}%>
                </div>
                <jsp:include page="Sidebar.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>

    </body>
</html>
