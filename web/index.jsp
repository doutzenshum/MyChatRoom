<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: santong
  Date: 15/6/28
  Time: 上午1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <!--
    Ask IE to behave like a modern browser
    See: https://www.modern.ie/en-us/performance/how-to-use-x-ua-compatible
-->
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>Chat Room</title>

    <!-- Disables zooming on mobile devices.-->
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <!--
    Stylesheet that minimizes browser differences.
    See: http://necolas.github.io/normalize.css/
-->
    <link rel="stylesheet" href="css/normalize.css" />

    <!--
作者：344234061@qq.com
时间：2015-06-17
描述：bootstrap
-->
    <link rel="stylesheet" href="css/bootstrap.min.css" />

    <!-- Our own stylesheet.-->
    <link rel="stylesheet" href="css/index.css" />

    <!-- 引入JQuery -->
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>

    <title>Welcome</title>
    <style>
    </style>
</head>

<body>
<div class="container">
    <div class="window">
        <s:form action="login" method="post">
            <p>
                <label>Username: </label>&nbsp;
                <input type="text" id="username" name="username" />
            </p>
            <p>
                <label>Password: </label>&nbsp;
                <input type="password" id="password" name="password" />
            </p>
            <p>
                <button type="submit" class="btn btn-primary" style="font-size: 15px; width: 300px;filter:alpha(opacity=80); -moz-opacity:0.8; -khtml-opacity: 0.8; opacity: 0.8;">Log in</button>
            </p>
            <p>
                <button type="submit" class="btn btn-primary" style="font-size: 15px; width: 300px;filter:alpha(opacity=80); -moz-opacity:0.8; -khtml-opacity: 0.8; opacity: 0.8;">Sign up</button>
            </p>
        </s:form>
    </div>
</div>
</body>

</html>