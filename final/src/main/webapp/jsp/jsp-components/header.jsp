
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>Portal</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400" rel="stylesheet">
    <style><%@include file="/css/logCss.css"%></style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath}/js/navJs.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <style><%@include file="/css/navCss.css"%></style>
    <style>
        .Myfooter {
        position: fixed;
        right: 0;
        color: white;
        bottom: 0;
        left: 0;
        padding: 1rem;
        text-align: center;
    }</style>
    <style>
        .searchButton {
        width: 40px;
        height: 36px;
        text-align: center;
        color: #fff;
        border-radius: 0 5px 5px 0;
        cursor: pointer;
        font-size: 20px;
    }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-md" style="background: #192f59;">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
            <img style="max-height: 50px" class="logo horizontal-logo" src="https://www.alem-edu.kz/wp-content/uploads/2020/05/unnamed-file.png">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <c:if test="${!cookie.user.value.equals('admin@admin.admin') && cookie.user.value.length()>0 && !sessionScope.role.equals('club moderator') }">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/clubServlet?&action=show">Clubs</a>
                    </li>
                </c:if>

                <c:if test="${!cookie.user.value.equals('admin@admin.admin') && cookie.user.value.length()>0 && !sessionScope.role.equals('event moderator') }">

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/eventServlet?&action=show">Events</a>
                    </li>
                    </c:if>
                    <c:if test="${!cookie.user.value.equals('admin@admin.admin') && cookie.user.value.length()>0 && !sessionScope.role.equals('news moderator') }">

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/newsServlet?&action=show">News</a>
                    </li>
                    </c:if>
                <c:if test="${cookie.user.value.equals('admin@admin.admin')}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/registration.jsp">Registration</a>
                </li>
                </c:if>
                <c:if test="${cookie.user.value.equals('admin@admin.admin')}">
                <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/search?&action=showAll">Users</a>
                    </li>
                </c:if>
                <c:if test="${cookie.user.value.equals('admin@admin.admin')}">
                <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/addModer.jsp">Add Moderators</a>
                    </li>
                </c:if>
                <c:if test="${cookie.user.value.equals('admin@admin.admin') || sessionScope.role.equals('club moderator')}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Clubs
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/clubServlet?&action=show">Show Clubs</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/club/addClub.jsp">Add Club</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/club/editClub.jsp">Edit Club</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/club/deleteClub.jsp">Delete Club</a>
                    </div>
                </li>
                </c:if>
                <c:if test="${cookie.user.value.equals('admin@admin.admin') || sessionScope.role.equals('event moderator')}">
                <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Events
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/eventServlet?&action=show">Show Events</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/event/addEvent.jsp">Add Event</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/event/editEvent.jsp">Edit Event</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/event/deleteEvent.jsp">Delete Event</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${cookie.user.value.equals('admin@admin.admin') || sessionScope.role.equals('news moderator')}">
                <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown4" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            News
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown4">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/newsServlet?&action=show">Show News</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/news/addNews.jsp">Add News</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/news/editNews.jsp">Edit News</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/news/deleteNews.jsp">Delete News</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${cookie.user.value.length()>0}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                </li>
                </c:if>
                <c:if test="${cookie.user.value == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a>
                    </li>
                </c:if>
                <c:if test="${cookie.user.value == null}">
                    <script>
                        if("http://localhost:8080/finalADJ_war_exploded/jsp/login.jsp" != window.location.href){
                            window.open("${pageContext.request.contextPath}/jsp/login.jsp", "_parent");
                        }
                    </script>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
