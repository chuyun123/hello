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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>

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
	height: 40px;
	width: 240px;
	font-size: 20px
}

span{color: red}

.right{   text-align: right; width: 220px;  }
</style>

</head>

<body>

	<div >
		<form name="searchForm" id="searchForm"
			action="<%=request.getContextPath()%>/doaddstandard.html"
			method="get">
			<h1 style="text-align:center">增加标准信息</h1>
			<table border="1" width=440px style="margin:0px auto">
				<tr><td class="right"><span>*</span>标准号:</td><td><input type="text" id="stdNum" name="stdNum" value=""/></td></tr>
				<tr><td class="right"><span>*</span>中文名称:</td><td><input type="text" name="zhname" value=""/></td></tr>
				<tr><td class="right"><span>*</span>版本:</td><td><input type="text" name="version" value=""/></td></tr>
				<tr><td class="right"><span>*</span>关键字/词:</td><td><input type="text" name="keys" value=""/></td></tr>
				<tr><td class="right">发布日期（yyyy-MM-dd）:</td><td><input name="releaseDate" id="releaseDate"   type="text" value=""/></td></tr>
				<tr><td class="right">实施日期（yyyy-MM-dd）:</td><td><input name="implDate" id="implDate"    type="text" value=""/></td></tr>
                <tr><td class="right"><span>*</span>附件:</td><td><input type="file" name="packagePath"   value="选择文件"/></td></tr>
				<tr><td colspan="2">&nbsp;  <input  style="width: 100px; margin-left: 100px" type="submit" value="保存" id="save"/> &nbsp; <input style="width: 100px;" type="reset" value="取消"/></td></tr>
			</table>
		</form>
	</div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>

	<script>
	//var stdNum=document.getElementsByName("stdNum");
		$(document).ready(function() {
		stdNum = $("#stdNum");
		
		stdNum.bind("blur",function(){
		alert();
		//ajax后台验证--userCode是否已存在
		//user.do?method=ucexist&userCode=**
		alert($(stdNum).val());
		if($(stdNum).val()==""){
			alert("标准号不能为空！");
		}
		$.ajax({
			type:"GET",//请求类型
			url:"/StuSystem"+"/ucexist.html",//请求的url
			data:{stdNum:stdNum.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
			//alert("www");
				if(data.stdNum == "exist"){//账号已存在，错误提示
					alert("标准号重复！");
					return;//不return 就一直死循环的发送请求了
				}else if(data.stdNum=="noexist"){
				alert("标准号可用！");
				return;
				}
			}
		/* 	error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("发生错误！");
			} */
		});
		});
		
		$("#releaseDate").blur(function(){
			alert("6666！");
			 var mobile = $(this).val();
			  var reg=/^[1-2]\d{3}[-]\d{2}[-]\d{2}/;
			  if(reg.test(mobile)==false){
			  alert("格式要求：yyyy-MM-dd！");
			//  $("#releaseDate").next().val("格式为：yyyy-MM-dd");
			  
			  }
		});
		$("#implDate").blur(function(){
			alert("777！");
			 var mobile = $(this).val();
			  var reg=/^[1-2]\d{3}[-]\d{2}[-]\d{2}/;
			  if(reg.test(mobile)==false){
			  alert("格式要求：yyyy-MM-dd！");
			 // $("#releaseDate").next().val("格式为：yyyy-MM-dd");
			  
			  }
			  
		
		});
		
		/* 
		$("form").submit(function() {
				
				 $("#releaseDate").blur(function(){
                var mobile = $(this).val();
                var $mobileId = $("#divMobile");
                yyyy-MM-dd
                var tes=/^[1-2]\d{3}[-]\d{2}[-]\d{2}/;
                var regMobile = /^1\d{10}$/;
                if (regMobile.test(mobile) == false) {
                    $mobileId.html("手机号码不正确，请重新输入");
                    return false;
                }
               $mobileId.html("");
                return true;
            }) */
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				if ($("#cityId").val() == 0) {
					alert("请选择所在城区");
					return false;
				}
				return true;
			});
	</script>

</html>
