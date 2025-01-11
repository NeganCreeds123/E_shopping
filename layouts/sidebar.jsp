<%-- 
    Document   : sidebar
    Created on : May 18, 2023, 2:20:52 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<aside class="menu-sidebar d-none d-lg-block">
    <div class="logo">
        <a href="#">
            <img src="<c:url value="/images/icon/logo.png" />" alt="Cool Admin" />
        </a>
    </div>
    <div class="menu-sidebar__content js-scrollbar1">
        <nav class="navbar-sidebar">
            <ul class="list-unstyled navbar__list">
                <li>
                    <c:choose>
                        <c:when test="${not empty LOGIN_USER && LOGIN_USER.role.equals('AD')}">
                            <a class="js-arrow" href="<c:url value='/home/index.do' />">
                                <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                            </c:when>
                            <c:when test="${not empty LOGIN_USER && LOGIN_USER.role.equals('CS')}">
                            <a class="js-arrow" href="<c:url value='/home/user.do' />">
                                <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                            </c:when>
                        </c:choose>
                </li>
                <li>
                    <c:choose>
                        <c:when test="${not empty LOGIN_USER && LOGIN_USER.role.equals('AD')}">
                            <a class="js-arrow" href="<c:url value='/order/history.do' />">
                                <i class="fa fa-shopping-cart"></i>Order</a>
                            </c:when>
                            <c:when test="${not empty LOGIN_USER && LOGIN_USER.role.equals('CS')}">
                            <a class="js-arrow" href="<c:url value='/user/payin.do' />">
                                <i class="fa fa-credit-card"></i>Top up</a>
                            </c:when>
                        </c:choose>
                </li>


                <li>
                    <c:choose>
                        <c:when test="${not empty LOGIN_USER && LOGIN_USER.role.equals('AD')}">
                            <a href="/statistic/table.html">
                                <i class="fa fa-link"></i>Transaction</a>
                            </c:when>

                        <c:when test="${not empty LOGIN_USER && LOGIN_USER.role.equals('CS')}">
                            <!--                            Enter link here-->
                            <a href="<c:url value='/user/transaction.do' />">
                                <i class="fa fa-link"></i>Transaction</a>
                            </c:when>
                        </c:choose>
                </li>
                <li>
                    <c:choose>
                        <c:when test="${not empty LOGIN_USER && LOGIN_USER.role.equals('AD')}">
                            <a href="<c:url value='/cus/customer.do' />">
                                <i class="fa fa-user"></i>Customer</a>
                            </c:when>

                    </c:choose>
                </li>
                <c:if test="${not empty LOGIN_USER && LOGIN_USER.role.equals('AD')}">
                    <li class="has-sub">
                        <a class="js-arrow" href="<c:url value="/item/view.do"/>">
                            <i class="fa fa-list"></i>Product</a>


                    </li>
                    <li class="has-sub">
                        <a class="js-arrow" href="<c:url value="/item/category.do"/>">
                            <i class="fa fa-tags"></i>Category</a>


                    </li>
                </c:if>

            </ul>
        </nav>
    </div>
</aside>
