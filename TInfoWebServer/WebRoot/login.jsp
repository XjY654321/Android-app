<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
		    <base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>Login</title>
		<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/menu.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/global.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/modal.js" type="text/javascript" charset="utf-8"></script>		
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" charset="utf-8" />
</head>
<body>
		<div id="wrapper_login">
			<div id="menu">
				<div id="left"></div>
				<div id="right"></div>
				<h2>登陆</h2>
				<div class="clear"></div>		
			</div>
			<div id="desc">
				<div class="body">
					<div class="col w10 last bottomlast">
						<form method="get" action="servlet/AdminLogin"  onsubmit="return login()" id="fromLogin" name="fromLogin">
							<p>
								<label for="username">用户名:</label>
								<input type="text" name="username" id="username" value="" size="40" class="text" style="width: 240px;" />
								<br />
							</p>
							<p>
								<label for="password">密码:</label>
								<input type="password" name="password" id="password" value="" size="40" class="text" style="width: 240px;"/>
								<br />
							</p>
							<p class="last">
								<small class="icon play"><input type="submit" value="Login" class="button form_submit"/></small>
								<br />
							</p>				
						</form>
					</div>
					<div class="clear"></div>
				</div>

				<div class="clear"></div>
				<div id="body_footer">
					<div id="bottom_left"><div id="bottom_right"></div></div>
				</div>
			</div>	
				
		</div>
		

<script type="text/javascript"> 
     function login()
     {
      var sUserName = document.fromLogin.username.value;
      var sPassword = document.fromLogin.password.value;
      if( sUserName=="" )
      {
       alert("请输入用户名！");

       return false;
      }else if( sPassword=="" )
      {
       alert("请输入密码！");
       return false;
      }else{
     
      }
     }
    </script> 
</body>
</html>