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
				<form class="layui-form" action="" id="register-form">
					<div class="layui-form-item">
					    <label class="layui-form-label">用户名称</label>
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
					      <button class="layui-btn" lay-submit="" lay-filter="register">立即提交</button>
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
					点击“立即提交”按钮，如果您的输入合法，系统将会提示您注册成功并跳转到登录页面登录，如果注册不成功，请点击菜单栏的留言菜单，向Ron留言，我会尽快答复您。
				</p>
			</blockquote>
		</div>
	</div>
	<jsp:include page="../include/i18nMsg.jsp"/>
	<script>
		layui.use(['form', 'layedit', 'laydate'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  ,layedit = layui.layedit
		  ,laydate = layui.laydate;
		  
		  //自定义验证规则
		  form.verify({
		    pass: [/(.+){6,12}$/, i18nMsg('common.validate.psw.length')]
		  });
		  
		  //监听提交
		  form.on('submit(register)', function(data){
		    $.ajax({
				type:"POST",
				url:"register/submitRegister",
				data:$("#register-form").serialize(),
				success: function(data){
					//layer.msg(data.resCode);
					switch(data.resCode){
					case '00':
						//注册成功
						location.href=$("base").attr("href")+"login";
						//layer.msg(i18nMsg('common.msg.success'), {icon: 1});
						break;
					case '03':{
						//验证失败
						var jsonData=data.data;
						$(jsonData).each(function(i){
							var obj=jsonData[i];
							var field=obj.field;
							var msg =obj.defaultMessage;
							layer.tips(i18nMsg(msg), "input[name='"+ field +"']", {
								  tipsMore: true
							}); 
						});
					}
						break;
					case '04':
						layer.tips(i18nMsg('common.validate.verifycode.error'), "input[name='verifyCode']", {
							  tipsMore: true
						}); 
						break;
					case '05':
						layer.tips(i18nMsg('common.validate.verifycode.expired'), "input[name='verifyCode']", {
							  tipsMore: true
						});
						break;
					case '99':
						layer.msg(i18nMsg('common.msg.failed'), {icon: 5,anim:6});
						break;
					default:
						layer.msg(i18nMsg('common.msg.failed'), {icon: 5,anim:6});
						break;
					}
				},
				error:function(msg){
					layer.msg(i18nMsg('common.msg.failed'), {icon: 5,anim:6});
				}
			});
		    
		    return false;
		  });
		}); 
		
		$(function(){
			$(".btn-getVerifyCode").click(function(){
				var email = $("input[name='userEmail']").val();
				if(email==""){
					layer.msg(i18nMsg('common.validate.email.blank'), {icon: 5,anim:6});
				}else{
					if(!REG_EMAIL.test(email)){
						layer.msg(i18nMsg('common.validate.email.format.error'), {icon: 5,anim:6});
						$("input[name='userEmail']").focus();
					}else{
						var loadindex = layer.load(2,{
							  shade: [0.5,'#000']
						});
						var json = {"email":email};
						//发送邮件获取验证码
						$.ajax({
							type:"POST",
							url:"register/getVerifyCode",
							data:json,
							dataType: "json",
							success: function(data){
								layer.close(loadindex);
								switch(data.resCode){
								case '00':
									layer.msg(i18nMsg('common.validate.verifycode.sendsuccess'), {icon: 1});
									$("input[name='verifyCodeUid']").val(data.data);
									break;
								case '01':
									layer.msg(i18nMsg('common.validate.email.blank'), {icon: 5,anim:6});
									break;
								case '02':
									layer.msg(i18nMsg('common.validate.email.exist'), {icon: 5,anim:6});
									break;
								case '99':
									layer.msg(i18nMsg('common.validate.verifycode.sendfailed'), {icon: 5,anim:6});
									break;
								}
							},
							error:function(msg){
								layer.close(loadindex);
								layer.msg(i18nMsg('common.msg.failed'), {icon: 5,anim:6});
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