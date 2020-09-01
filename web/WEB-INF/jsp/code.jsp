<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码使用演示</title>
<link rel="stylesheet" type="text/css" href="/css/code.css"/>
<script src="<%=basePath%>/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/js/code.js" type="text/javascript"></script>
<script>
	function getBasePath(){
	    return '<%=basePath%>';
    }
</script>
</head>
<body>
   <form>
       <div class="row">
           <label>手机号:</label><input name="number">
       </div>
       <div class="row">
           <label>模板ID:</label><input name="templateId">
       </div>
       <div class="row">
           <label>验证码:</label>
           <input name="verifyCode">
           <button type="button" class="sendVerifyCode">获取短信验证码</button>
       </div>
       <div><button type="button" class="sub-btn">提交</button></div>
   </form>
</body>
</html>