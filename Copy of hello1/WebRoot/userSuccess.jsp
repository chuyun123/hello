<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userSuccess.jsp' starting page</title>
    
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
    request.setCharacterEncoding("utf-8");
    
    response.setCharacterEncoding("utf-8");
   String username=request.getParameter("username");
   String password=request.getParameter("password");
   String[] hobbys=request.getParameterValues("hobby");
  if(username.equals("系统管理员")&&password.equals("123")){
  session.setAttribute("user", username);
  session.setMaxInactiveInterval(10);
  response.sendRedirect("userLogin.jsp");
 /*  request.getRequestDispatcher("success.jsp").forward(request, response); */
  }else{
  request.setAttribute("mess","登陆失败,重新登陆!");
  
  request.getRequestDispatcher("userLogin.jsp").forward(request, response);
  }
  
 
     %>
  </body>
</html>
