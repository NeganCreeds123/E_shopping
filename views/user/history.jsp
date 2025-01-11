<%-- 
    Document   : history
    Created on : Jun 8, 2023, 1:04:22 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<section class="vh-100" style="background-color: #f4f5f7;">
    <div class="container py-5">
         <h1 class="mb-4 text-center" style="margin-top: 90px!important">Payment History</h1>
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card mb-3">
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Date</th>
                                    <th>Amount</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>#001</td>
                                    <td>2023-06-01</td>
                                    <td>$50.00</td>
                                    <td><span class="badge badge-success">Completed</span></td>
                                </tr>
                                <tr>
                                    <td>#002</td>
                                    <td>2023-05-28</td>
                                    <td>$30.00</td>
                                    <td><span class="badge badge-success">Completed</span></td>
                                </tr>
                                <tr>
                                    <td>#003</td>
                                    <td>2023-05-22</td>
                                    <td>$20.00</td>
                                    <td><span class="badge badge-warning">Pending</span></td>
                                </tr>
                                <tr>
                                    <td>#004</td>
                                    <td>2023-05-15</td>
                                    <td>$40.00</td>
                                    <td><span class="badge badge-danger">Failed</span></td>
                                </tr>
                                <tr>
                                    <td>#005</td>
                                    <td>2023-05-10</td>
                                    <td>$25.00</td>
                                    <td><span class="badge badge-success">Completed</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

