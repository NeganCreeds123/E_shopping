<%-- 
    Document   : customer
    Created on : Jun 20, 2023, 12:48:05 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<div class="container mt-5">

    <table class="table table-striped table-hover" id="history">
                <thead>
                <tr>
                   
                    <th>Order ID</th>
                    <th>Customer</th>
                    <th>Date</th>
                    <th>Total</th>
                    <th>Show Detail</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" varStatus="counter" var="order">
                            <tr>
                              
                                <td>${order.id}</td>
                                <td>${order.name}</td>
                                <td>${order.date}</td>
                                <td>${order.total}VND</td>
                                <td>
                                    <a href="<c:url value='/order/detail.do?orderId=${order.id}'/>">More</a>
                                </td>
                            </tr>
                        </c:forEach>
                </tbody>
        </table>


</div>