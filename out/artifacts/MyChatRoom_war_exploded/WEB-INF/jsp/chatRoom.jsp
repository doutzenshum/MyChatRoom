<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--
HTML5. Use tags like <article>, <section>, etc.
See: http://www.sitepoint.com/web-foundations/doctypes/
-->
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
    <link rel="stylesheet" href="<s:url value='/css/normalize.css'/>"/>

    <!--
    作者：344234061@qq.com
    时间：2015-06-17
    描述：bootstrap
    -->
    <link rel="stylesheet" href="<s:url value='/css/main.css'/>"/>

    <!-- Our own stylesheet.-->
    <link rel="stylesheet" href="<s:url value='/css/bootstrap.min.css'/>"/>

    <!-- 引入JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            var speak = $("#speak");
            var username = $("#user");
            $("#send").click(function () {
                if (speak.val() == "") {//判断说话内容是否为空
                    alert("说话内容不可以为空！");
                    $("speak").focus();	//让说话内容文本框获得焦点
                }
                $.post("ChatServlet?action=send", {
                    user: username.val(),
                    speak: speak.val()
                });	//发送POST请求
                speak.val(""); //清空说话内容文本框的值
                speak.focus(); //让说话内容文本框获得焦点
            });
            getContent();
            window.setInterval("getContent();", 3000);	//每隔5秒钟获取一次聊天内容
        });
        //读取聊天内容
        function getContent() {
            $.get(${pageContext.request.contextPath}"/ChatServlet?action=get&nocache=" + new Date().getTime(),
                    function (data) {
                        $("#content").html(data);	//显示读取到的聊天内容
                    });
        }
    </script>
</head>
<body>

<div class="container">

    <div class="panel-title">
        <h2>Hi! <%=session.getAttribute("username")%></h2>
    </div>
    <div class="user_list">
        <input type="text" value="<%=session.getAttribute("username")%>" name="user" id="user"
               style="visibility: hidden; display: none"/>
        <div class="panel-body">
            <ul class="list-group">
                <s:iterator value="userlist">
                    <li class="list-group-item">
                        <s:property/>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>

    <div class="chat_view">
        <div class="chat_display" id="content">
        </div>
        <form name="form1" method="post" action="">
            <div class="chat_send">
                <div class="input-group">
                    <input type="text" class="form-control" id="speak" name="speak"
                           placeholder="Try to say something."> <span class="input-group-btn">
							<button class="btn btn-default" type="button" id="send">Send</button>
						</span>
                </div>
            </div>
        </form>
    </div>

</div>

<footer>
    <br />
    <p>Chat Online. </p>

    <p>Based on Struts2 and Hibernate.</p>

    <p>---- By Santong.</p>
</footer>

</body>

</html>
