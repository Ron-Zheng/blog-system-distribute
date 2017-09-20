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
			<div class="layui-col-md2">
				<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
					<li class="layui-nav-item"><a href="/user-home.html"> <i
							class="layui-icon"></i> 我的主页
					</a></li>
					<li class="layui-nav-item layui-this"><a href="/user.html">
							<i class="layui-icon"></i> 用户中心
					</a></li>
					<li class="layui-nav-item "><a href="/user-set.html"> <i
							class="layui-icon"></i> 基本设置
					</a></li>
					<li class="layui-nav-item "><a href="/user-message.html"> <i
							class="layui-icon"></i> 我的消息
					</a></li>
					<li class="layui-nav-item "><a href="/user-score.html"> <i
							class="layui-icon"></i> 积分日志
					</a></li>
					<li class="layui-nav-item "><a href="/user-get-score.html">
							<i class="layui-icon"></i> 赚取积分
					</a></li>
					<li class="layui-nav-item "><a href="/user-identity-apply.html">
							<i class="layui-icon"></i> 身份认证
					</a></li>
					<span class="layui-nav-bar"
						style="top: 32.5px; height: 0px; opacity: 0;"></span>
				</ul>
			</div>
			<div class="layui-col-md10">
				<div class="fly-user-main">
					<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
						<ul class="layui-tab-title">
						    <li class="layui-this">我的文章</li>
						    <li>阅读排行</li>
						</ul>
						<div class="layui-tab-content" style="height: 100px;">
						    <div class="layui-tab-item layui-show">内容不一样是要有，因为你可以监听tab事件（阅读下文档就是了）</div>
						    <div class="layui-tab-item">内容2</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		layui.use('element', function() {
			var element = layui.element;
		});
	</script>
</body>
</html>