<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main index_main content-first">
		<div class="single-main blog_content col-md-12">
			<div class="content_title">MySQL数据库如何删除表中部分关键字段重复的记录</div>
			<div class="content_aut_block">
				<div class="content_aut">
					<span style="float: left;"> <i style="color: #009688"
						class="layui-icon"></i>&nbsp; 作者: Ron
					</span> <span style="float: right;"> <i
						style="color: #ff7600; font-weight: bold;" class="layui-icon"></i>&nbsp;
						发布时间：2017-08-31 15:37:14
					</span>
				</div>
			</div>
			<div class="content">
				<p>MySQL数据库中如何删除部分关键字段重复的记录呢？本文我们通过一个例子来介绍这一删除方法，接下来我们先说一说这个例子。
				</p>
				<p>
					从名字layUI就可以看出，layUI是一款轻量级前端UI框架，大部分结构支持响应式。layUI发布于2016年秋天，最新版本layui2.0发布于2017年8月，也就最近。锵锵锵，热腾腾的layUI上桌了~~~
				</p>
				<p>
					layUI定义为“经典标准化”，其模式和AMD相似，所以在使用过程中，你会发现其有些使用方式和AMD，requireJS非常相似。但是更加简单的是，layUI以当前浏览器普通认可的方式去组织模块。
				</p>
				<p>
					获得layui后，将其完整地部署到你的项目目录（或静态资源服务器），你只需要引入下述两个文件（这样加载的方式不用去管其他任何文件。因为各模块文件都是在最终使用到的时候才会自动加载。也就是说你要使用的模块文件位置必须准确。）：
				</p>
			</div>
		</div>

		<div class="single-main blog_content">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>欢迎评论</legend>
				<textarea class="layui-textarea" id="comment_edit" style="display: none">  
				  把编辑器的初始内容放在这textarea即可
				</textarea>
				<div class="site-demo-button" style="margin-top: 20px;">
					<button class="layui-btn site-demo-layedit" data-type="content">提交评论</button>
				</div>
			</fieldset>
			<div class="blog-module-title">最新评论</div>
			<ul class="blog-comment">
				<c:forEach items="${blogComments}" var="comment" varStatus="status">
					<li class="item${status.index + 1}">
						<div class="comment-parent">
							<img src="images/layui-icon.jpg">
							<div class="info">
								<span class="username">${comment.comUserName}</span> <span class="time"><fmt:formatDate value="${comment.comDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
							</div>
							<div class="comment-content">
								<p>
									${comment.comContent}
								</p>
							</div>
						</div>
					</li>
					<script type="text/javascript">
						var getReplyList=function(index){
							var json = {"commentCode":index};
							$.ajax({
								type:"POST",
								url:"blog/getCommentReply",
								data:json,
								dataType: "json",
								success: function(data){
									if(data.resCode=='00'){
										var jsonData = data.data;
										$(jsonData).each(function(i){
											var obj = jsonData[i];
											//渲染第一层
											if(obj.pid == '0'){
												var html = getHtml(obj,0);
												$(".item"+index).append(html);
												if(obj.children=='1'){
													//如果有子节点，渲染子节点
													renderChildren(obj,jsonData,index,0);
												}
											}
										});
									}
								}
							});
						};
						
						//递归渲染子节点
						var renderChildren=function(obj,jsonData,index,level){
							$(jsonData).each(function(i){
								var cobj = jsonData[i];
								if(cobj.pid==obj.id){
									var html = getHtml(cobj,++level);
									$(".item"+index).append(html);
									if(cobj.children=='1'){
										//如果有子节点，渲染子节点
										renderChildren(cobj,jsonData,index,level);
									}else{
										return;
									}
								}
							});
						};
						
						//组装html
						var getHtml = function(obj,level){
							var date = new Date(obj.replyDate);
							var html = "<div class='comment-parent' style='padding-left:"+ level*20 +"px'>";
							
							html += "<div class='info'>";
							html += "<span class='username'>"+ obj.replyUserName +"</span>回复 <span class='username'>"+ obj.repliedUserName +"</span>";
							html += "<span class='time'>"+ date.format("yyyy-MM-dd hh:mm:ss") +"</span>";							
							html += "</div>";
							
							html += "<div class='comment-content'>";
							html += "<p>" + obj.replyContent + "</p>";
							html += "</div>";
							
							html += "</div>";
							return html;
						};
						
						getReplyList(${status.index + 1});
					</script>
				</c:forEach>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		layui.use('layedit', function() {
			var layedit = layui.layedit, $ = layui.jquery;

			//构建一个默认的编辑器
			var index = layedit.build('comment_edit', {
				height : 200
			});

			//编辑器外部操作
			var active = {
				content : function() {
					//alert(layedit.getContent(index)); //获取编辑器内容
					//提交评论
				}
			};

			$('.site-demo-layedit').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});
	</script>
</body>
</html>