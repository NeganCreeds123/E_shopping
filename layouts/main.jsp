<%-- 
    Document   : main
    Created on : Feb 2, 2023, 12:51:25 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->s
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Dashboard</title>

    <!-- Fontfaces CSS-->
   <link href="<c:url value="/css/font-face.css" />" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/font-awesome-4.7/css/font-awesome.min.css" />" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/font-awesome-5/css/fontawesome-all.min.css" />" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/mdi-font/css/material-design-iconic-font.min.css" />" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Vendor CSS-->
    <link href="<c:url value="/vendor/animsition/animsition.min.css" />" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" />" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/wow/animate.css" />" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/css-hamburgers/hamburgers.min.css" />" rel="stylesheet" media="all">
    <link rel="stylesheet" href="<c:url value='/css/history.css' />" />
      <link rel="stylesheet" href="<c:url value='/css/history_details.css' />" />



    <!-- Main CSS-->
    <link href="<c:url value="/css/theme.css" />" rel="stylesheet" media="all">
      <link href="<c:url value="/css/style.css" />" rel="stylesheet" media="all">

</head>
<body class="animsition" style="animation-duration: 900ms; opacity: 1;">
    <div class="page-wrapper">
        <!-- HEADER MOBILE-->
         
        <!-- END HEADER MOBILE-->

        <!-- MENU SIDEBAR-->
        <%@include file="sidebar.jsp" %>
       
        <!-- END MENU SIDEBAR-->
        <!-- PAGE CONTAINER-->
        <div class="page-container">
            <!-- HEADER DESKTOP-->
             <%@include file="header.jsp" %>
          
            <!-- HEADER DESKTOP-->

            <!-- MAIN CONTENT-->
            
            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />
            <!-- END MAIN CONTENT-->
            <!-- END PAGE CONTAINER-->
  

        </div>

    </div>

    <!-- Jquery JS-->
   <script src="<c:url value="/vendor/jquery-3.2.1.min.js" />"></script>
<!-- Bootstrap JS-->
<script src="<c:url value="/vendor/bootstrap-4.1/popper.min.js" />"></script>
<script src="<c:url value="/vendor/bootstrap-4.1/bootstrap.min.js" />"></script>
<!-- Vendor JS -->
<script src="<c:url value="/vendor/slick/slick.min.js" />"></script>
<script src="<c:url value="/vendor/wow/wow.min.js" />"></script>
<script src="<c:url value="/vendor/animsition/animsition.min.js" />"></script>
<script src="<c:url value="/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js" />"></script>
<script src="<c:url value="/vendor/counter-up/jquery.waypoints.min.js" />"></script>
<script src="<c:url value="/vendor/counter-up/jquery.counterup.min.js" />"></script>
<script src="<c:url value="/vendor/circle-progress/circle-progress.min.js" />"></script>
<script src="<c:url value="/vendor/perfect-scrollbar/perfect-scrollbar.js" />"></script>
<script src="<c:url value="/vendor/chartjs/Chart.bundle.min.js" />"></script>
<script src="<c:url value="/vendor/select2/select2.min.js" />"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.7.14/dist/vue.js"></script>
<script src="<c:url value="/js/main.js" />"></script>
</body>

</html>
<!-- end document-->