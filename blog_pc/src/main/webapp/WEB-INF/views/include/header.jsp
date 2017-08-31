<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
	<div class="main">
		<ul class="layui-nav nav">
		  <li class="layui-nav-item"><a href="">首页</a></li>
		  <li class="layui-nav-item">
		    <a href="javascript:;">博客分类</a>
		  </li>
		  <li class="layui-nav-item">
		    <a href="">关于Ron<span class="layui-badge-dot"></span></a>
		  </li>
		  <li class="layui-nav-item">
		    <a href="">留言</a>
		  </li>
		  <li class="layui-nav-item">
		    <a href="">
		    	<img src="images/layui-icon.jpg" class="layui-nav-img">
		    	您还未登录
		    	<span class="layui-nav-more"></span>
		    </a>
		    <dl class="layui-nav-child">
		      <dd><a href="user/login">登录</a></dd>
		      <dd><a href="user/register">注册</a></dd>
		    </dl>
		  </li>
		</ul>
	</div>
</div>

<script>
	//注意：导航 依赖 element 模块，否则无法进行功能性操作
	layui.use('element', function(){
	  var element = layui.element;
	});
</script>