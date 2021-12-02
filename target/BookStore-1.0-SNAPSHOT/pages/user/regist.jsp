<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        //页面加载完成之后
        $(function () {
            $("#code_img").click(function () {
                this.src = "${basePath}/kaptcha.jpg?d=" + new Date();
            });
            $("#sub_btn").click(function () {
                //验证用户名
                var usernameText = $("#username").val();
                var usernamepattern = /^\w{6,12}$/;
                if (!usernamepattern.test(usernameText)) {
                    $("span.errorMsg").text("用户名不合法！");
                    return false;
                }
                $("span.errorMsg").text("");
                //验证密码
                var passwordText = $("#password").val();
                var passwordpattern = /^\w{6,12}$/;
                if (!passwordpattern.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法！");
                    return false;
                }
                $("span.errorMsg").text("");
                //验证密码
                var repasswordText = $("#repwd").val();
                if (repasswordText !== passwordText) {
                    $("span.errorMsg").text("密码不一致！");
                    return false;
                }
                $("span.errorMsg").text("");
                //验证邮箱
                var emailText = $("#email").val();
                const emailPattern = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPattern.test(emailText)) {
                    $("span.errorMsg").text("邮箱格式不合法！");
                    return false;
                }
                $("span.errorMsg").text("");

                var codeText = $("#code").val();
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    $("span.errorMsg").text("请输入验证码！");
                    return false;
                }
            });
        });

    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>

</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        <%=request.getAttribute("msg") == null ? "请输入以下信息进行注册！" : request.getAttribute("msg")%>
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="<%=request.getAttribute("username") == null ? "" : request.getAttribute("username")%>"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"
                               value="<%=request.getAttribute("password") == null ? "" : request.getAttribute("password")%>"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"
                               value="<%=request.getAttribute("password") == null ? "" : request.getAttribute("password")%>"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="<%=request.getAttribute("email") == null ? "" : request.getAttribute("email")%>"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 120px;" name="code" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 100px; height: 40px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>