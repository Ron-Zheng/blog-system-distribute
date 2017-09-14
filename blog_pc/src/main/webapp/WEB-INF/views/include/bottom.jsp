<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="layui-footer footer">
	<div class="main">
		<p>Ron博客系统</p>
		<p>Powered by Ron</p>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		var winh=$(window).height();
		var offtop = $(".footer").offset().top;
		if(winh-offtop-80 > 0){
			$(".footer").css("margin-top",winh-offtop-80);
		}
	});
</script>