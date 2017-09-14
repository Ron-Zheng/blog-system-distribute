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
		<div class="layui-row">
			<div class="layui-col-md8 page_left">
				<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief"
					style="margin: 0px 0px 10px 0px; background: #fff;">
					<ul class="layui-tab-title">
						<li class="layui-this">留言</li>
						<li>网友留言</li>
					</ul>
					<div class="layui-tab-content">
						<div class="layui-tab-item layui-show">
							<fieldset class="layui-elem-field layui-field-title"
								style="margin-top: 20px;">
								<legend>请输入您的留言信息</legend>
							</fieldset>
							<form class="layui-form">
								<div class="layui-form-item">
									<label class="layui-form-label">留言标题</label>
									<div class="layui-input-block">
										<input type="text" name="title" required lay-verify="required"
											placeholder="请输入标题" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">留言内容</label>
									<div class="layui-input-block" style="height:300px;">
										<textarea id="content" name="layedit_content" style="display: none;"></textarea>
									</div>
								</div>
								<div class="layui-form-item">
								    <div class="layui-input-block">
								      <button class="layui-btn" lay-submit="" lay-filter="submit">提交</button>
								    </div>
								</div>
							</form>
						</div>
						<div class="layui-tab-item">留言列表
							<script>alert('你是不是傻？')</script>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4 page_right">
				<div class="right_item">
					<h3>关于博主</h3>
					<ol class="page_right_list trans_3">
						<li>姓名：郑云红</li>
						<li>English Name：Ron.Zheng</li>
						<li>职业：Java程序员、架构师</li>
						<li>CSDN 博客地址：<a target="_blank"
							href="http://blog.csdn.net/zyhlwzy">http://blog.csdn.net/zyhlwzy</a>
						</li>
					</ol>
				</div>
				<div class="right_item">
					<h3>最新文章</h3>
					<ol class="page_right_list trans_3">
						<li><a href="#">Spring技术栈-博客系统基础架构</a><span>562 ℃</span></li>
						<li><a href="#">Spring技术栈-博客系统基础架构</a><span>562 ℃</span></li>
						<li><a href="#">Spring技术栈-博客系统基础架构</a><span>562 ℃</span></li>
						<li><a href="#">Spring技术栈-博客系统基础架构</a><span>562 ℃</span></li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/i18nMsg.jsp" />
	<script>
		//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
		layui.use('element', function() {
			var element = layui.element;
		});
		layui.use(['form','layedit'], function(){
			var form = layui.form,layer = layui.layer,layedit = layui.layedit;
			var index = layedit.build('content',{
				  height: 250,
				  tool: ['strong','italic','underline','del','|','left','center','right','link','unlink','face']
			  }); //建立编辑器
			  
			layedit.sync(index);
			  
			//监听提交
			form.on('submit(submit)', function(data){
				var content = layedit.getContent(index);
				$("textarea[name='layedit_content']").val(content);
				//layer.msg(content);
				//提交留言
				$.ajax({
				type:"POST",
				url:"msg/sendMsg",
				data:$(".layui-form").serialize(),
				success: function(data){
					//layer.msg(data.resCode);
					switch(data.resCode){
						case '00':
							layer.msg(i18nMsg('common.msg.success'), {
								time:0,
								icon: 1,
								btn:[i18nMsg('layer.btn.confirm')],
								yes:function(){
									location.href=location.href;   
								}
							});
							break;
						case '01':
							layer.msg(i18nMsg('common.validate.messages.empty'), {icon: 5,anim:6});
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
	</script>
</body>
</html>