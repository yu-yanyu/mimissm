<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxfileupload.js"></script>
	</head>

	<body>
		<div id="addAll">
			<div id="nav">
				<p>订单管理>订单商品</p>
			</div>
			<script type="text/javascript">
				function fn() {
					$("#numerr").empty();
					var pNum = $("#pNum").val();
					var pName = $("#pName").val();
					if(isNaN(pNum)&&pNum.length>0){
						var text = document.createTextNode("请输入数字！");
						$("#numerr").append(text);
					}else {
						$.ajax({
							url:"${pageContext.request.contextPath}/orders/calculation.action",
							data:{"pNum":pNum,"pName":pName},
							type:"get",
							dataType:"text",
							success:function (data) {
								$("#money").prop("value",data);
							}
						});
					}
				}
				function fn1() {
					$("#moneyerr").empty();
					var money = $("#money").val();
					if(isNaN(money)&&money.length>0){
						var text = document.createTextNode("请输入数字！");
						$("#moneyerr").append(text);
					}
				}
			</script>
<script type="text/javascript">
	function myclose() {
		window.location="${pageContext.request.contextPath}/orders/split.action";
		//window.close();
	}
</script>
			<div id="table">
				<form action="${pageContext.request.contextPath}/orders/update.action" enctype="multipart/form-data" method="post" id="myform">
					<input type="hidden" value="${orders.oId}" name="oId">
					<input type="hidden" value="${page}" name="page">
					<table>
						<tr>
							<td class="one">客户</td>
							<td><input type="text" name="oName" class="two" value="${orders.oName}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>
						<tr>
							<td class="one">商品</td>
							<td>
								<select name="pName" id="pName">
									<c:forEach items="${plist}" var="p">
										<option value="${p}"
												<c:if test="${p==orders.pName}">
													selected="selected"
												</c:if>
										>${p}</option>

									</c:forEach>
								</select>
							</td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>

						<tr>
							<td class="one">数量</td>
							<td><input type="text" value="${orders.pNum}" name="pNum" id="pNum" class="two" onblur="fn()"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="numerr"></span></td>
						</tr>
						<tr>
						<tr>
							<td class="one">金额</td>
							<td><input type="text" value="${orders.money}" name="money" id="money" class="two" onblur="fn1()"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="moneyerr"></span></td>
						</tr>
						<tr>
						<tr>
							<td class="one">地址</td>
							<td><input type="text" value="${orders.oAddress}" name="oAddress" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>
						<tr>
						<tr>
							<td class="one">电话</td>
							<td><input type="text" name="phone" value="${orders.phone}" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>
						<tr>
						<tr>
							<td class="one">商品</td>
							<td>
								<select name="paystate" id="paystate">
									<option value="0"
											<c:if test="${orders.paystate==0}">
										selected="selected"
									</c:if>>未支付</option>
									<option value="1" <c:if test="${orders.paystate==1}">
										selected="selected"
									</c:if>>已支付</option>
								</select>
							</td>
						</tr>

						<tr>
							<td>
								<input type="submit" value="提交" class="btn btn-success">
							</td>
							<td>
								<input type="reset" value="取消" class="btn btn-default" onclick="myclose()">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>

</html>