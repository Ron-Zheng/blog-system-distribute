<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main index_main content-first">
		<div class=" single-main">
			<blockquote class="layui-elem-quote layui-text">
			欢迎成为Ron博客用户，请填写以下信息登录，谢谢配合。</blockquote>
			
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			  <legend>请输入您的登录信息</legend>
			</fieldset>
			<div class="ui-responsive-center">
				<form class="layui-form" action="" id="register-form">
				    <div class="layui-form-item">
				      <label class="layui-form-label">用户邮箱</label>
				      <div class="layui-input-block">
				        <input type="email" name="userEmail" lay-verify="required|email" autocomplete="off" placeholder="请输入邮箱" class="layui-input">
				      </div>
				    </div>
				    <div class="layui-form-item">
					    <label class="layui-form-label">请输入密码</label>
					    <div class="layui-input-inline">
					      <input type="password" name="userLoginPassword" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
					    </div>
					    <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
					</div>
					<div class="layui-form-item">
					    <label class="layui-form-label">验证码</label>
					    <div class="layui-input-inline">
					      <input type="text" name="verifyCode" lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
					    </div>
					    <button class="layui-btn layui-btn-normal btn-getVerifyCode">获取验证码</button>
					</div>
					<div class="layui-form-item">
					    <div class="layui-input-block">
					      <button class="layui-btn" lay-submit="" lay-filter="register">登录</button>
					      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					    </div>
					</div>
					<input type="hidden" name="verifyCodeUid" >
				</form>
			</div>
			<blockquote class="layui-elem-quote layui-text">
				<p>
					点击“获取验证码”按钮，我们会向您的邮箱发送一份验证码邮件，请根据邮件内容填写验证码，谢谢。
				</p>
				<p>
					点击“登录”按钮，如果您的输入合法，系统将会提示您登录成功并跳转到您的个人信息页面，如果登录不成功，请点击菜单栏的留言菜单，向Ron留言，我会尽快答复您。
				</p>
			</blockquote>
		</div>
	</div>
	<jsp:include page="../include/i18nMsg.jsp"/>
</body>
</html>