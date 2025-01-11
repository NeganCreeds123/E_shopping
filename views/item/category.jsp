<%-- 
    Document   : view
    Created on : May 19, 2023, 12:43:45 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

    <div class="container">

        <div class="row" style="margin-top: 60px">

            <div class="col-lg-12">
<div class="alert ${status}" role="alert">
  ${message}
</div>
                <div class="main-box clearfix">
                    <div class="table-responsive">
                        <table class="table user-list">
                            <thead>
                                <tr>

                                    <th><span>ID</span></th>
                                    <th><span>Name</span></th>
                                    <th><span>Status</span></th>
                                    <th class="text-center"><span>Action</span></th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="category" items="${category}">
                                <form action="<c:url value="/ItemHandleController"/>">

                                    <tr>
                                        <td>${category.id}</td><input type="hidden" name="id" value="${category.id}"/>
                                    <td><input type="text" name="name" value="${category.name}"/> </td>
                                    <td><select name="status" >
                                            <option value="Display" ${category.status == 'Display' ? 'selected' : ''}>Display</option>
                                            <option value="Delete" ${category.status == 'Delete' ? 'selected' : ''}>Delete</option>
                                        </select></td>
                                    <td class="text-center">
                                        <button name="op" value="update" type="submit" class="table-link success">
                                            <span class="fa-stack">
                                                <i class="fa fa-square fa-stack-2x"></i>
                                                <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                            </span>
                                        </button>

                                    </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>


                </div>
 <button class="btn btn-primary mt-2" type="button" data-toggle="modal" data-target="#myModal">Add category</button>
            </div>
            <!-- Button to open the modal -->
           
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Add category</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form class="form" action="<c:url value="/ItemHandleController" />" novalidate="">
                                
                                <div class="row">
                                    
                                    <div class="col">
                                        <div class="row">
                                            <div class="col">
                                                <div class="form-group">
                                                    <label>Category Name</label>
                                                    <input class="form-control" type="text" name="name" >
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="form-group">
                                                    <label>Category Status</label>
                                                    <div class="form-group">

                                                        <div class="form-check-inline">
                                                            <input type="radio" id="display" name="status" value="Display">
                                                            <label for="display">Display</label>
                                                        </div>
                                                        <div class="form-check-inline">
                                                            <input type="radio" id="del" name="status" value="Delete">
                                                            <label for="del">Delete</label>
                                                        </div>
                                                    </div>

                                                </div>


                                            </div>
                                        </div>


                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col d-flex justify-content-end">
                                        <button class="btn btn-primary" name="op" value="addCategory" type="submit">Save Changes</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
