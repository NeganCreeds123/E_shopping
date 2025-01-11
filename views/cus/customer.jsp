<%-- 
    Document   : customer
    Created on : Jun 20, 2023, 12:48:05 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container">
<div class="row bootstrap snippets bootdeys"> 
    <div class="col-md-9 col-sm-7"> 
        <h2>Members</h2> 
    </div> 
    <div class="col-md-3 col-sm-5"> 
        <form method="get" role="form" class="search-form-full"> 
            <div class="form-group"> 
                <input type="text" class="form-control" name="s" id="search-input" placeholder="Search..."> 
                <i class="entypo-search"></i> 
            </div> 
        </form> 
    </div> 
</div>
    <c:forEach var="cus" items="${cusList}">
    <div class="member-entry"> 
    
    <div class="member-details"> 
        <h4> <a href="#">${cus.name}</a> </h4> 
        <div class="row info-list"> 
            <div class="col-sm-4"> 
                <i class="fa fa-user"></i>
                <a href="#">CustomerID: ${cus.id}</a> 
            </div> 
            <div class="col-sm-4"> 
                <i class="fa fa-twitter"></i> 
                <a href="#">@ ${cus.userId}</a> 
            </div> 
            <div class="col-sm-4"> 
                <i class="fa fa-phone" aria-hidden="true"></i>
                <a href="#">${cus.phoneNum}</a> 
            </div> 
            <div class="clear"></div> 
            <div class="col-sm-4"> 
                <i class="fa fa-location"></i> 
                <a href="#">UserID: ${cus.userId}</a> 
            </div> 
            <div class="col-sm-4"> 
                <i class="fa fa-envelope"></i> 
                <a href="#">${cus.email}</a> 
            </div> 
            <div class="col-sm-4"> 
                <i class="fa fa-link"></i>
                <a href="#">View Payment History!</a> 
            </div> 
        </div> 
    </div> 
</div>
    </c:forEach>


</div>