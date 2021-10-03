<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<title></title>
		<script type="text/javascript">
			function fn() {
				var name = $("#myname").val();
				$.ajax({
					url:"${pageContext.request.contextPath}/admin/repeat.action",
					type:"post",
					data:{"name":name},
					success:function (msg) {
					$("#nametd").empty();
					var msg = document.createTextNode(msg);
					$("#nametd").append(msg);
					}
				});
			}
		</script>
	</head>

	<body>
		<div id="login">
			<div id="top">
				<img src="${pageContext.request.contextPath}/images/cloud.jpg" /><span>REGIST</span>
			</div>
			<div id="bottom">
				<form action="${pageContext.request.contextPath}/admin/regist.action" method="post">
					<table border="0px" id="table">
						<tr>
							<td class="td1">用户名：</td>
							<td ><input type="text" placeholder="Username" value="" class="td2" onblur="fn()" id="myname" name="myname">
							<br>
								<font id="nametd" color="red"></font></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="nameerr"></span></td>
						</tr>
						<tr>
							<td class="td1">密码：</td>
							<td><input type="password" placeholder="Password" value="" class="td2" name="mypwd"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="pwderr"></span></td>
						</tr>
						<tr>
							<td><a href="login.jsp" ><input type="button" value="返回" class="td4"></a></td>
							<td><input type="submit" value="注册" class="td3">
								<input type="reset" value="清除" class="td3	">
							</td>
						</tr>
					</table>
				</form>
				${errmsg}
			</div>

		</div>
	</body>

</html>