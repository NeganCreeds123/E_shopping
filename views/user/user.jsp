<%-- 
    Document   : user
    Created on : Jun 1, 2023, 2:08:10 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    /* Modal styles */
   .modal {
        display: none; /* Hide the modal by default */
        position: fixed; /* Position it as fixed */
        z-index: 9999; /* Set a high z-index to ensure it appears on top of other elements */
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto; /* Enable scrolling if needed */
        background-color: rgba(0, 0, 0, 0.4); /* Add a semi-transparent background */
    }

    .modal-content {
        background-color: #fefefe;
        margin: auto;
        padding: 20px;
        border: 1px solid #888;
        width: 400px; /* Set the width to 400px */
        height: 400px; /* Set the height to 400px */
        border-radius: 15px;
        position: relative;
    }

    .close {
        position: absolute;
        top: 10px;
        right: 10px;
        color: #aaa;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>
<section class="h-100" style="background-color: #eee;">
    <div class="container py-5 h-100"  style="margin-top: 60px!important">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-12 col-xl-5">

                <div class="card" style="border-radius: 15px;">
                    <div class="card-body text-center">
                        <div class="mt-3 mb-4">
                            <img  id="qrImage" src="<c:url value='/images/icon/user.png' />" alt="QR Code"
                                 class="rounded-circle img-fluid" style="width: 150px;" />
                        </div>
                        <h4 class="mb-2">${USER_INFO.name}</h4>
                        <p class="text-muted mb-4">@Programmer <span class="mx-2">|</span> <a
                                href="#!">mdbootstrap.com</a></p>
                        <div class="mb-4 pb-2">
                            <button type="button" class="btn btn-outline-primary btn-floating">
                                <i class="fab fa-facebook-f fa-lg"></i>
                            </button>
                            <button type="button" class="btn btn-outline-primary btn-floating">
                                <i class="fab fa-twitter fa-lg"></i>
                            </button>
                            <button type="button" class="btn btn-outline-primary btn-floating">
                                <i class="fab fa-skype fa-lg"></i>
                            </button>
                        </div>
                        <form action="<c:url value="/GenerateQRController" />"  id="generateQRForm">
                            <input type="hidden" value="${LOGIN_USER.userID}" name="userId"/>
                            <button  type="submit" class="btn btn-primary btn-rounded btn-lg">
                                Show QR
                            </button>
                        </form>
                            
                        <div class="d-flex justify-content-between text-center mt-5 mb-2">
                            <div>
                                <p class="mb-2 h5">8471</p>
                                <p class="text-muted mb-0">Wallets Balance</p>
                            </div>
                            <div class="px-3">
                                <p class="mb-2 h5">8512</p>
                                <p class="text-muted mb-0">Income amounts</p>
                            </div>
                            <div>
                                <p class="mb-2 h5">4751</p>
                                <p class="text-muted mb-0">Total Transactions</p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>

