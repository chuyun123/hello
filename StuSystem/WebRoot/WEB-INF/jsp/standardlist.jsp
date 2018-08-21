<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'standardlist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
	/*  div{
  border: 1px   crimson solid;
            height: auto;
            width: 500px;
            margin: 50px auto;
            padding: 50px;
            box-shadow: 1px 2px 1px  #4b7580;
            border-radius: 10px;
            box-sizing: border-box;
} */
	h2{
	text-align: center;
	}
	a{
	color: red;
	}
	</style>
	
	

  </head>
  <body>
  <div >
		<h1  style="text-align: center; margin-left:260px">标准信息列表</h1> 
	<p class="search">
		<form method="get" action="${pageContext.request.contextPath }/standardList.html">
	
	<input name="param" class="input-text"type="text" id="aparam" value="${aparam}">  
    <input type="hidden" name="pageIndex" id="pageIndex" value="1" /> 
	<input value="提交检索" type="button" id="searchbutton">
	<input value="新增标准" type="button" id="addbutton">
	<input value="删除标准" type="button" id="deletbutton">
		</form>
	
	<!--用户-->
	<table border="1" width=900px  class="providerTable" cellpadding="0" cellspacing="0">
		<tr class="firstTr">
		    <th width="10%"><input type="checkbox" name="allcheckbox" id="allcheckbox" ></th>
			<th width="10%">标准号</th>
			<th width="20%">中文名字</th>
			<th width="10%">版本</th>
			<th width="10%">发布日期</th>
			<th width="10%">实施日期</th>
			<th width="10%">附件路径</th>
			<th width="10%">操作</th>
		</tr>
		<c:forEach var="standard" items="${standardList}" varStatus="status">
				<input type="hidden" name="standardId" id="standardId" value="${standard.id}"/>
			<tr <c:if test="${status.count%2==0}">    style="background-color: #C0C0C0 " </c:if> >
			<td><span><input type="checkbox" name="checkstandard" id="${standard.id }" value="${standard.id }" ></span></td>
			<td><span>${standard.stdNum}</span></td>
				<td><span>${standard.zhname}</span></td>
				<td><span>${standard.version}</span></td>
				<td><span>${standard.releaseDate}</span></td>
				<td><span>${standard.implDate}</span></td>
				<td><span>${standard.packagePath}</span></td>
				<td><span><a class="viewstandard" 
						>下载</a>
				<a href="updateStandard.html?id=${standard.id}">修改</a>
				</span> </td>
			</tr>
		</c:forEach>
	</table> 
	<input type="hidden" id="totalPageCount" value="${totalPageCount}" />
	     <c:import url="rollpage.jsp">
		<c:param name="totalCount" value="${totalCount}" />
		<c:param name="currentPageNo" value="${currentPageNo}" />
		<c:param name="totalPageCount" value="${totalPageCount}" />
	</c:import>
	</div>
  </body>
 <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<script type="text/javascript">

$(function(){

$("#searchbutton").bind("click",function(){
alert("333");
		window.location.href="/StuSystem"+"/standardList.html?aparam="+$("#aparam").val()
		+"&pageIndex="+$("#pageIndex").val();
	});

$("#addbutton").bind("click",function(){

		window.location.href="/StuSystem"+"/addstandard.html";
	});

$("#deletbutton").on("click",function(){
		var dele=$("#delete");
		$("#standardId").val();
		window.location.href="/StuSystem"+"/deleteStandard.html";
	});
});

$(function(){

	$("#deletbutton").on("click", function() {
		let id_array = new Array();
		$("[name='checkstandard']:checked").each(function() {
			id_array.push($(this).val());
		})
		var ids = id_array.join(",");
		window.location.href ="/StuSystem"+"/delete.html?id="+ids;
		/* $.ajax({
			type : "get",
			url : "/StuSystem/delete.html",
			data : {id:ids},
			dataType:"text",
			success :function (data) {
		if(data=="true"){
		 window.location.href ="/StuSystem"+"/standardList.html?pageIndex=1"; 
		} if(data=="false"){
		alert("刪除失敗");
		}
	},
	error:  function (data){
	alert(1);
	}
		});
	});

 */
});


	$(function(){
	
	$("#allcheckbox").on("click", function() {
		if ($(this).is(':checked')) {
			$("[name='checkstandard']").prop("checked","checked");
		} else {
			$("[name='checkstandard']").removeProp("checked");
		}



});
	
	
	});


</script>



</html>






