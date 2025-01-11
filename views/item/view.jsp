<%-- 
    Document   : customer
    Created on : Jun 20, 2023, 12:48:05 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<div class="container">

    <div class="alert ${status} mt-5" role="alert">
        ${message}
    </div>
    <div class="main-box clearfix">
        <div class="table-responsive">

            <a  href="<c:url value="/item/add.do"/>" class="btn-info pl-3 pr-3 pt-2 pb-2  table-link" style="border-radius: 15px"><i class="fa fa-plus mr-2" aria-hidden="true"></i>ADD</a>
            <table class="table user-list">
                <thead>
                    <tr>

                        <th><span>ID</span></th>
                        <th><span>Name</span></th>
                        <th class="text-center"><span>Update Image</span></th>
                        <th class="text-center"><span>Status</span></th>
                        <th><span>Price</span></th>
                        <th><span>Action</span></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>


                                <a href="#" class="user-link">${product.name}</a>

                            </td>
                            <td class="">
                                <c:if test="${not empty product.image}">
                                    <img src="<c:url value="/images/product/${product.image}" />" alt="">
                                    <form action="<c:url value="/FileUploadController" />" enctype="multipart/form-data" method="post">
                                        <input class="form-control" type="hidden" name="file_name" value="${product.name}">
                                        <input type="file" name="file2" />
                                        <input type="submit" value="upload" />
                                    </form> 
                                </c:if>
                                <c:if test="${empty product.image}">
                                    <form action="<c:url value="/FileUploadController" />" enctype="multipart/form-data" method="post">
                                        <input class="form-control" type="hidden" name="file_name" value="${product.name}">
                                        <input type="file" name="file2" />
                                        <input type="submit" value="upload" />
                                    </form> 
                                </c:if>

                            </td>
                            <td class="text-center">
                                <span class="label label-default">${product.status}</span>
                            </td>
                            <td>
                                <a href="#">${product.price} vnd</a>
                            </td>
                            <td style="width: 20%;">

                                <a href="<c:url value="/item/edit.do?id=${product.id}"/>" class="table-link">
                                    <span class="fa-stack">
                                        <i class="fa fa-square fa-stack-2x"></i>
                                        <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                    </span>
                                </a>
                                <c:choose>
                                    <c:when test="${product.status eq 'Delete'}">
                                        <a href="<c:url value='/ItemHandleController?op=display&amp;id=${product.id}'/>" class="table-link success">
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-eye fa-stack-1x fa-inverse"></i>
                                            </span>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="<c:url value="/ItemHandleController?op=delete&id=${product.id}"/>" class="table-link danger">
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                            </span>
                                        </a>
                                    </c:otherwise>
                                </c:choose>



                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>

        </div>


    </div>


</div>