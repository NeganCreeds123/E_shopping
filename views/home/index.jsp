<%-- 
    Document   : index
    Created on : Feb 6, 2023, 10:09:20 AM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="/js/graph.js"></script>
<c:if test="${LOGIN_USER eq null || LOGIN_USER.role ne 'AD'}"> 
    <c:redirect url="account/login.do"></c:redirect>
</c:if>
<div class="main-content">
    <div class="section__content section__content--p30">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6 col-lg-3">
                    <div class="statistic__item">
                        <h2 class="number">${NUM_OF_CUSTOMERS}</h2>
                        <span class="desc">Registered Customers</span>
                        <div class="icon">
                            <i class="zmdi zmdi-account-o"></i>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="statistic__item">
                        <h2 class="number">${ITEMS_SOLD}</h2>
                        <span class="desc">Items sold</span>
                        <div class="icon">
                            <i class="zmdi zmdi-shopping-cart"></i>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="statistic__item">
                        <h2 class="number">${NUM_OF_ORDERS}</h2>
                        <span class="desc">Number of Orders</span>
                        <div class="icon">
                            <i class="zmdi zmdi-calendar-note"></i>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="statistic__item">
                        <h2 class="number">${TOTAL}</h2>
                        <span class="desc">Total earnings</span>
                        <div class="icon">
                            <i class="zmdi zmdi-money"></i>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="au-card m-b-30">
                        <div class="au-card-inner">
                            <h3 class="title-2 m-b-40">Line Chart</h3>
                            <canvas id="numOfItems" style="width:100%;max-width:700px;"></canvas>
                            <script>
                                // Retrieve the graph value from the session
                                var graphValue = ${graphValue};

                                var xVal = graphValue.map(entry => entry.date);
                                var yVal = graphValue.map(entry => entry.total);



                                new Chart("numOfItems", {
                                    type: "bar",
                                    data: {
                                        labels: xVal,
                                        datasets: [{
                                                backgroundColor: "blue",
                                                data: yVal
                                            }]
                                    },
                                    options: {
                                        legend: {display: false},
                                        title: {
                                            display: true,
                                            text: "Order Statistics"
                                        }
                                    }
                                });
                                function generateColors(length) {
                                    var colors = [];
                                    for (var i = 0; i < length; i++) {
                                        var color = getRandomColor();
                                        colors.push(color);
                                    }
                                    return colors;
                                }

                                // Function to generate a random color
                                function getRandomColor() {
                                    var letters = "0123456789ABCDEF";
                                    var color = "#";
                                    for (var i = 0; i < 6; i++) {
                                        color += letters[Math.floor(Math.random() * 16)];
                                    }
                                    return color;
                                }
                            </script>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="au-card m-b-30">
                        <div class="au-card-inner">
                            <h3 class="title-2 m-b-40">Pie Chart</h3>
                            <canvas id="PieChart" style="width:100%;max-width:500px;"></canvas>

                            <script>
                                var pieChartValue = ${pieChartValue};
                                var xValues = pieChartValue.map(entry => entry.name);
                                var yValues = pieChartValue.map(entry => entry.quantity);
                                var barColors = generateColors(pieChartValue.length);

                                new Chart("PieChart", {
                                    type: "pie",
                                    data: {
                                        labels: xValues,
                                        datasets: [{
                                                backgroundColor: barColors,
                                                data: yValues
                                            }]
                                    },
                                    options: {
                                        title: {
                                            display: true,
                                            text: "Percentage sales of products"
                                        }
                                    }
                                });

                                // Function to generate an array of random colors

                            </script>
                        </div>
                    </div>
                </div>
            </div>



            <div class="row">
                <div class="col-md-12">
                    <div class="copyright">
                        <p>Copyright Â© 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

