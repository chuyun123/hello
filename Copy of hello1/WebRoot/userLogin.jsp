<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userLogin.jsp' starting page</title>
    <style type="text/css">
    input{
    magin:10px;
    }
    
    </style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   
   <%
   String name1 =(String)request.getAttribute("mess");
   String name =(String)session.getAttribute("user");
   
   if(name!=null){
   out.println("欢迎您："+name+"<br/>");
            	out.println(session.getId());
            	%>
           	<a href="index.jsp">注销</a>
           	<%
   }else if(name1==null){
   out.println(name+"<br/>");
    %>
    <form action="userSuccess.jsp" method="post">
   用户：<input type="text" value="" name="username"/><br/>
       密码： <input type="password" value="" name="password"/><br/>
   <input type="submit" value="提交"/>
   </form><%  } %>
  </body>
</html>
