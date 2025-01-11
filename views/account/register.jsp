<%-- 
    Document   : register
    Created on : May 18, 2023, 4:05:18 PM
    Author     : Dell
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<body class="animsition">
    <div class="page-wrapper">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <div class="login-logo">
                            <a href="#">
                                <img src="<c:url value="/images/icon/logo.png" />" alt="CoolAdmin">
                            </a>
                        </div>
                        <div class="login-form">
                            <form action="<c:url value="/RegisterController" />" method="post">
                                 <div class="form-group">
                                    <div class="text-center text-danger">${message}</div>
                                </div>
                                <div class="form-group">
                                    <label>Email Address</label>
                                    <input class="au-input au-input--full" type="email" name="email" placeholder="Email">
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input class="au-input au-input--full" type="text" name="name" placeholder="Username">
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-group">
                                            <label>Phone Number</label>
                                            <input class="au-input au-input--full" type="tel" name="phone" placeholder="Phone number">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input class="au-input au-input--full" type="password" name="password" placeholder="Password">
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-group">
                                            <label>Confirm Password</label>
                                            <input class="au-input au-input--full" type="password" name="cfPassword" placeholder="Confirm Password">
                                        </div>
                                    </div>
                                </div>

                                <div class="login-checkbox">
                                    <label>
                                        <input type="checkbox" name="aggree">Agree the terms and policy
                                    </label>
                                </div>
                                <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">register</button>
                                <div class="social-login-content">
                                    <div class="register-link">
                                        <p>
                                            Already have account?
                                            <a href="<c:url value="/account/login.do"/>">Sign In</a>
                                        </p>
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

<!-- end document-->