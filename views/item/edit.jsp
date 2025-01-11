<%-- 
    Document   : add
    Created on : May 19, 2023, 12:41:30 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<body class="animsition">
    <div class="page-wrapper">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <h1 class="font-weight-bold  text-center">Edit Product</h1>
                        <div class="">
                            <form class="form" action="<c:url value="/ItemHandleController" />" enctype="multipart/form-data" >


                                <div class="alert ${status}" role="alert">
                                    ${message}
                                </div>
                                <div class="row">

                                    <div class="col">

                                        <div class="form-group">
                                            <label>Item Name</label>
                                            <input class="form-control" type="text" name="name" placeholder="Tra-xanh" value="${pro.name}" >
                                            <input class="form-control" type="hidden" name="id" value="${pro.id}" >
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-group">
                                            <label>Item Category</label>
                                            <select name="category"  class="form-control" aria-label="Default select example">
                                                <c:forEach var="category" items="${category}">
                                                    <option value="${category.id}" <c:if test="${pro.categoryID eq category.id}">selected</c:if>>${category.name}</option>
                                                </c:forEach>

                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="form-group">
                                            <label>Item Price</label>
                                            <input class="form-control" type="number" name="price" placeholder="20.000 vnd/chai" value="${pro.price}"  >
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-group">
                                            <label>Status</label>
                                            <div class="form-group">

                                                <div class="form-check-inline">
                                                    <input type="radio" id="display" name="status" value="Display"<c:if test="${pro.status eq 'Display'}"> checked </c:if>>
                                                        <label for="display">Display</label>
                                                    </div>
                                                    <div class="form-check-inline">
                                                        <input type="radio" id="del" name="status" value="Delete"<c:if test="${pro.status eq 'Delete'}"> checked </c:if>>
                                                        <label for="del">Delete</label>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                 


                                <div class="row mb-3">
                                    <div class="col d-flex justify-content-end">
                                        <button class="btn btn-primary" name="op" value="edit" type="submit">Save Changes</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

</body>



