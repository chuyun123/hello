<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>My JSP 'showContext.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
#ss {
	border: 1px blue solid;
	border-radius: 10px;
	width: 700px;
	margin: 50px auto;
}

input {
	height: 30px;
	width: 300px;
	font-size: 20px
}

tr{height: 40px;}
.right{   text-align: right;width: 220px;  }

span{color: red}
</style>

</head>

<body>

	<div>
		<form name="searchForm" id="searchForm"
			action="<%=request.getContextPath()%>/doaddstandard.html"
			method="get">

			<h1 style="text-align:center">修改标准信息</h1>
			<table border="1" width=900px style="margin:0px auto">
				<tr><td class="right"><span>*</span>标准号:</td><td><input type="text" name="stdNum" value="${standard.stdNum}"/></td></tr>
				<tr><td class="right"><span>*</span>中文名称:</td><td><input type="text" name="zhname" value="${standard.zhname}"/></td></tr>
				<tr><td class="right"><span>*</span>版本:</td><td><input type="text" name="version" value="${standard.version}"/></td></tr>
				<tr><td class="right"><span>*</span>关键字/词:</td><td><input type="text" name="keys" value="${standard.keys}"/></td></tr>
				<tr><td class="right"><span>*</span>发布日期（yyyy-MM-dd）:</td><td><input name="releaseDate" type="text" value="${standard.releaseDate}"/></td></tr>
				<tr><td class="right"><span>*</span>实施日期（yyyy-MM-dd）:</td><td><input name="implDate" type="text" value="${standard.implDate}"/></td></tr>
                <tr><td class="right"><span>*</span>附件:</td><td><input type="file" name="packagePath"   value="${standard.packagePath}"/></td></tr>
				<tr><td colspan="2">&nbsp; &nbsp; &nbsp;<input style="width: 100px; margin-left: 100px" type="submit" value="保存"/>&nbsp; &nbsp; &nbsp; <input style="width: 100px;" type="reset" value="取消"/></td></tr>
			</table>
		</form>

	</div>

	<script type="text/javascript" src="jquery-1.12.4.js"></script>
	<script>
		$(document).ready(function() {
			$("form").submit(function() {

				if ($("#cityId").val() == 0) {
					alert("请选择所在城区");
					return false;
				}
				return true;
			});
		});
	</script>

</body>
</html>
