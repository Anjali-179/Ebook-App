<%@page import ="java.sql.*"%>
<%@ page import ="com.DB.DBConnect" %>
<%@ page import="com.entity.newbook" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> E-book : Index</title>
    <%@include file="all_component/allCSS.jsp"%>
    <style type="text/css">
        .back-img{
            background: url("IMG/Book.jpg");
            height: 50vh;
            width:100%;
            background-repeat:no-repeat;
            background-size:cover;
        }
    </style>
</head>
<body style="background-color:#f7f7f7;">

<%@ include file = "all_component/navlogin.jsp" %>
<div class ="container-fluid back-img">
    <h2 class="text-center text-primary">EBook Management System</h2>
</div>
<style>
    .card-container {
        display: inline-block;
        flex-wrap: wrap;
        justify-content:space-between ;
        margin-top: 20px;
    }
    .card {
        display: inline-flex;
        width: 300px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    .card-body {
        padding: 15px;

    }

    img{
        width: 150px;
        height: 200px;
    }

</style>
</body>
<body>
<%-- Iterate over the list of books and generate card HTML for each book --%>
<div class="container">
    <div class="card-container">
        <%
            List<newbook> myList = (List<newbook>) request.getAttribute("myList");
            for (newbook book : myList) {
        %>
        <div class="col-md-3">
            <div class="card">
                <div class="card-body text-center">
                    <img src="<%=book.getBookImage()%>" class="img-thumblin"  >
                    <p> <%= book.getAuthor() %></p>
                    <p style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; max-width: 200px;"><%= book.getBook_description()%></>
                    <div class="button-container">
                        <a href="" class="btn btn-danger btn_sm mx-1">View Details</a>
                        <a href="" class="btn btn-danger btn_sm mx_1 "><%= book.getBook_price()%></a>

                        <!-- Add more properties as needed -->
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>

<%@include file="all_component/footer.jsp" %>

</body>
</html>