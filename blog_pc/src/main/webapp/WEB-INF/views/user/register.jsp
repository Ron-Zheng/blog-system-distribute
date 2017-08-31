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
			欢迎注册成为Ron博客用户，亲爱的，您现在来，还不算晚，请填写以下信息注册，谢谢配合。</blockquote>
			
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			  <legend>请输入您的注册信息</legend>
			</fieldset>
			<div class="ui-responsive-center">
				<form class="layui-form" action="">
					<div class="layui-form-item">
					    <label class="layui-form-label">登录名称</label>
					    <div class="layui-input-block">
					      <input type="text" name="userLoginName" lay-verify="required" autocomplete="off" placeholder="请输入登录名称" class="layui-input">
					    </div>
					</div>
				    <div class="layui-form-item">
				      <label class="layui-form-label">用户邮箱</label>
				      <div class="layui-input-block">
				        <input type="email" name="userEmail" lay-verify="required|email" autocomplete="off" placeholder="请输入邮箱" class="layui-input">
				      </div>
				    </div>
				    <div class="layui-form-item">
					    <label class="layui-form-label">请输入密码</label>
					    <div class="layui-input-inline">
					      <input type="password" name="password" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
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
					      <button class="layui-btn" lay-submit="" lay-filter="register">立即提交</button>
					      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					    </div>
					</div>
				</form>
			</div>
			<blockquote class="layui-elem-quote layui-text">
				<p>
					点击“获取验证码”按钮，我们会向您的邮箱发送一份验证码邮件，请根据邮件内容填写验证码，谢谢。
				</p>
				<p>
					点击“立即提交”按钮，如果您的输入合法，系统将会提示您注册成功并跳转到登录页面登录，如果注册不成功，请点击菜单栏的留言菜单，向Ron留言，我会尽快答复您。
				</p>
			</blockquote>
		</div>
	</div>
	<script>
		layui.use(['form', 'layedit', 'laydate'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  ,layedit = layui.layedit
		  ,laydate = layui.laydate;
		  
		  //自定义验证规则
		  form.verify({
		    pass: [/(.+){6,12}$/, '密码必须6到12位']
		  });
		  
		  //监听提交
		  form.on('submit(register)', function(data){
		    layer.alert(JSON.stringify(data.field), {
		      title: '最终的提交信息'
		    })
		    
		    return false;
		  });
		});
		
		$(function(){
			$(".btn-getVerifyCode").click(function(){
				var email = $("input[name='userEmail']").val();
				if(email==""){
					layer.msg('请输入邮箱', {icon: 5,anim:6});
					$("input[name='userEmail']").focus();
				}else{
					if(!REG_EMAIL.test(email)){
						layer.msg('邮箱格式有误', {icon: 5,anim:6});
						$("input[name='userEmail']").focus();
					}else{
						var json = {"email":email};
						//发送邮件获取验证码
						$.ajax({
							type:"POST",
							url:"user/getVerifyCode",
							data:json,
							dataType: "json",
							success: function(data){
								switch(data.resCode){
								case '00':
									layer.msg('验证码已发送，请注意查收！', {icon: 1});
									break;
								case '01':
									layer.msg('请输入邮箱', {icon: 5,anim:6});
									break;
								case '02':
									layer.msg('邮箱已经存在，您不能注册', {icon: 5,anim:6});
									break;
								case '99':
									layer.msg('系统错误', {icon: 5,anim:6});
									break;
								}
							},
							error:function(msg){
								layer.msg('获取验证码出错', {icon: 5,anim:6});
							}
						});
					}
				}
				return false;
			});
		});
	</script>
</body>
</html>