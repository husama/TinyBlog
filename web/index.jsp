<%--
  Created by IntelliJ IDEA.
  User: husama
  Date: 16-8-28
  Time: 上午7:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>胡湘铭的博客|Coding And Thinking</title>

    <!-- Bootstrap -->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  <nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#example-navbar-collapse">
          <span class="sr-only">切换导航</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">胡湘铭的博客</a>
      </div>
      <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">C++</a></li>
          <li><a href="#">Python</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              Java <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
              <li><a href="#">Spring</a></li>
              <li><a href="#">EJB</a></li>
              <li><a href="#">Java并发</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>
