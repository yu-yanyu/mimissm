<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <script type="text/javascript">
        if ("${msg}" != "") {
            alert("${msg}");
        }
    </script>

    <c:remove var="msg"></c:remove>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bright.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <title></title>
</head>
<script type="text/javascript">
    function allClick() {
        //取得全选复选框的选中未选 中状态
        var flag=$("#all").prop("checked");
        //将此状态赋值给每个商品列表里的复选框
        $("input[name=ck]").each(function () {
            this.checked=flag;
        });
    }

    function ckClick() {
        //取得所有name=ck的被选中的复选框
        var length=$("input[name=ck]:checked").length;
        //取得所有name=ck的复选框
        var len=$("input[name=ck]").length;
        //比较
        if(len == length){
            $("#all").prop("checked",true);
        }else
        {
            $("#all").prop("checked",false);
        }
    }
</script>
<body>
<div id="brall">
    <div id="nav">
        <p>订单管理>订单列表</p>
    </div>
    <div id="condition" style="text-align: center">
        <form id="myform">
            客户名称：<input name="oname" id="oname">&nbsp;&nbsp;&nbsp;
            商品：<select name="pname" id="pname">
            <option value="-1">请选择</option>
            <c:forEach items="${plist}" var="pt">
                <option value="${pt}">${pt}</option>
            </c:forEach>
        </select>&nbsp;&nbsp;&nbsp;
            支付状态：<select name="paystate" id="paystate">
                <option value="-1">请选择</option>
            <option value="1">已支付</option>
            <option value="0">未支付</option>
            </select>&nbsp;&nbsp;&nbsp;
            地址：<input name="address" id="address">&nbsp;&nbsp;&nbsp;
            <input type="button" value="查询" onclick="condition()">
    </div>


    <br>
    <div id="table">

        <c:choose>
            <c:when test="${o_info.list.size()!=0}">

                <div id="top">
                    <input type="checkbox" id="all" onclick="allClick()" style="margin-left: 50px">&nbsp;&nbsp;全选
                    <a href="${pageContext.request.contextPath}/admin/addorders.jsp">

                        <input type="button" class="btn btn-warning" id="btn1"
                               value="添加订单">
                    </a>
                    <input type="button" class="btn btn-warning" id="btn1"
                           value="批量删除" onclick="deleteBatch()">
                </div>
                <!--显示分页后的商品-->
                <div id="middle">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <th></th>
                            <th>客户名</th>
                            <th>商品名</th>
                            <th>数量</th>
                            <th>金额</th>
                            <th>地址</th>
                            <th>电话</th>
                            <th>支付状态</th>
                            <th>操作</th>

                        </tr>
                        <c:forEach items="${o_info.list}" var="o">
                            <tr>
                                <td valign="center" align="center"><input type="checkbox" name="ck" id="ck" value="${o.oId}" onclick="ckClick()"></td>
                                <td>${o.oName}</td>
                                <td>${o.pName}</td>
                                <td>${o.pNum}</td>
                                <td>${o.money}</td>
                                <td>${o.oAddress}</td>
                                <td>${o.phone}</td>
                                <td><C:if test="${o.paystate==1}">已支付</C:if>
                                    <C:if test="${o.paystate==0}">未支付</C:if>
                                        </td>
                                <td>
                                    <button type="button" class="btn btn-info "
                                            onclick="one(${o.oId},${o_info.pageNum})">编辑
                                    </button>
                                    <button type="button" class="btn btn-warning" id="mydel"
                                            onclick="del(${o.oId})">删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <!--分页栏-->
                    <div id="bottom">
                        <div>
                            <nav aria-label="..." style="text-align:center;">
                                <ul class="pagination">
                                    <li>
                                            <%--<a href="${pageContext.request.contextPath}/prod/split.action?page=${info.prePage}" aria-label="Previous">--%>
                                        <a href="javascript:ajaxsplit(${o_info.prePage})" aria-label="Previous">

                                            <span aria-hidden="true">«</span></a>
                                    </li>
                                    <c:forEach begin="1" end="${o_info.pages}" var="i">
                                        <c:if test="${o_info.pageNum==i}">
                                            <li>
                                                    <%--<a href="${pageContext.request.contextPath}/prod/split.action?page=${i}" style="background-color: grey">${i}</a>--%>
                                                <a href="javascript:ajaxsplit(${i})"
                                                   style="background-color: grey">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${o_info.pageNum!=i}">
                                            <li>
                                                    <%--<a href="${pageContext.request.contextPath}/prod/split.action?page=${i}">${i}</a>--%>
                                                <a href="javascript:ajaxsplit(${i})">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li>
                                        <%--  <a href="${pageContext.request.contextPath}/prod/split.action?page=1" aria-label="Next">--%>
                                        <a href="javascript:ajaxsplit(${o_info.nextPage})" aria-label="Next">
                                            <span aria-hidden="true">»</span></a>
                                    </li>
                                    <li style=" margin-left:150px;color: #0e90d2;height: 35px; line-height: 35px;">总共&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${o_info.pages}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${o_info.pageNum!=0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${o_info.pageNum}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                        <c:if test="${o_info.pageNum==0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">1</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    <h2 style="width:1200px; text-align: center;color: orangered;margin-top: 100px">暂时没有符合条件的商品！</h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</div>
</body>

<script type="text/javascript">

    //批量删除
    function deleteBatch() {

            //取得所有被选中删除商品的pid
            var cks=$("input[name=ck]:checked");
            //判断是否有选中的商品
            if(cks.length==0){
                // 没有选中的商品
                alert("请选择将要删除的商品！");
            }else{
                var str="";
                var id="";
                // 有选中的商品，则取出每个选中商品的ID，拼接提交的ID的数据
                if(confirm("您确定删除"+cks.length+"条商品吗？")){
                //拼接ID
                    $.each(cks,function () {
                        id=$(this).val(); //22 33
                        //非空判断，防止出错
                        if(id!=null)
                            str += id+",";  //22,33,44
                    });
                    //发送ajax请求
                   $.ajax({
                       url:"${pageContext.request.contextPath}/orders/deleteBatch.action",
                       data:{"ids":str},
                       type:"post",
                       dataType: "text",
                       success:function (msg) {
                           alert(msg);
                           $("#table").load("http://localhost:8080/admin/orders.jsp #table");
                       }
                   });
                }
        }
    }
    //单个删除
    function del(pid) {
        if (confirm("确定删除吗")) {
        $.ajax({
            url: "${pageContext.request.contextPath}/orders/deleteBatch.action",
            data: {"ids":pid},
            type: "post",
            dataType:"text",
            success:function (msg) {
                alert(msg);
                $("#table").load("http://localhost:8080/admin/orders.jsp #table");
            }
        });
        }
    }

    function one(oid,page) {
        location.href = "${pageContext.request.contextPath}/orders/one.action?oid=" + oid+"&page="+page;
    }
</script>
<!--分页的AJAX实现-->
<script type="text/javascript">
    var pname = -1;
    var oname = '';
    var address = '';
    var paystate = -1;
    function ajaxsplit(page) {
        //向服务发出ajax请求,请示page页中的所有数据,在当前页面上局部刷新显示
        $.ajax({
            url: "${pageContext.request.contextPath}/orders/ajaxSplit.action",
            data:{"pname":pname,"oname":oname,"address":address,"paystate":paystate,"page":page},
            type: "post",
            success: function () {
                //重新加载显示分页数据的容器
                $("#table").load("http://localhost:8080/admin/orders.jsp #table");
            }
        });

    }


    function condition() {
        //取出查询条件
        pname = $("#pname").val();
        oname = $("#oname").val();
        address = $("#address").val();
        paystate = $("#paystate").val();

        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/orders/ajaxSplit.action",
            data:{"pname":pname,"oname":oname,"address":address,"paystate":paystate,"page":1},
            success:function () {
                //刷新显示数据的容器
                $("#table").load("http://localhost:8080/admin/orders.jsp #table");
            }
        });
    }
</script>

</html>