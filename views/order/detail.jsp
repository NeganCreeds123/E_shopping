<%-- 
    Document   : detail
    Created on : Oct 29, 2021, 1:47:34 PM
    Author     : KawaiiScorpy
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <body >
        <table id="detail" style="margin-top: 60px">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${detail}" var="d">
                    <tr>
                        <td>${d.product.name}</td>
                        <td><img class="product_image"src="${d.product.image}"></td>
                        <td>${d.product.price}$</td>
                        <td>${d.quantity}</td> 
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>

