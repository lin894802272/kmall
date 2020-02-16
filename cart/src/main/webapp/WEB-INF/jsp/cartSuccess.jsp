<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" type="text/css"
	href="/css/base.css">
<link href="/css/initcart20150123.css" type="text/css"
	rel="stylesheet">
	<link href="/css/public.css" rel="stylesheet"/>
	<link href="/css/cz.css" rel="stylesheet"/>
	<!--引入iconfont-->
	<link rel="stylesheet" href="/css/iconfont.css">
	<!--引入swiper-->
	<link rel="stylesheet" href="/css/swiper.min.css">
<title>商品已成功加入购物车</title>
<style id="style-1-cropbar-clipper">
/* Copyright 2014 Evernote Corporation. All rights reserved. */
.en-markup-crop-options {
	top: 18px !important;
	left: 50% !important;
	margin-left: -100px !important;
	width: 200px !important;
	border: 2px rgba(255, 255, 255, .38) solid !important;
	border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
	margin-left: 0px !important;
}

/*模板头部样式*/
.cz_top{
	position: relative;
	width:100%;
	height: 45px;
	line-height:45px;
	background: #28292B;
}
.cz_top h1{
	float:left;
	width:20px;
	height: 20px;
	margin-left:22px;
	margin-top:12.5px;
}
.cz_top h1 a{
	display: block;
	width:100%;
	height: 100%;
	color:#E5E5E5;
}
.cz_top h1 a img{
	display: block;
	width:100%;
	height: 100%;
}
.cz_top .top_list{
	float:left;
	min-width:800px;
	height: 100%;
	margin-left:360px;
}
.cz_top .top_list ul{
	display: flex;
}
.cz_top .top_list ul li{
	float: left;
	padding:0 20px;
	font-size:12px;
}
.cz_top .top_list ul li a{
	color:#BFBFC0;
	text-decoration: none;
}
.cz_top .top_list ul li:nth-child(1) a{
	color:#fff;
}
.cz_top .top_list ul li a:hover{
	color:#fff;
}
.cz_top .top_sign{
	float:right;
	width:80px;
	margin-right:25px;
}
.cz_top .top_sign p{
	float:left;
	width:20px;
	height: 20px;
	padding:0 10px;
	color:#BFBFC0;
	cursor:pointer;
}
.cz_top .top_sign p i{
	display: block;
	width:100%;
	height: 100%;
}
.iconfont {
	font-family: "iconfont" !important;
	font-size: 16px;
	font-style: normal;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}
.icon-denglu{
	content: "\e603";
}
.icon-gouwuche{
	content: "\e63f";
}

</style>
</head>
<body class="root61">
<div class="cz_top">
	<h1><a href="http://localhost:8082"><img src="/img/top/logo.png" alt=""></a></h1>
	<div class="top_sign">
		<p><i class="iconfont icon-denglu" onclick="javascript:window.location.href='http://localhost:8082/user/showLogin';return false;"></i></p>
		<p><i class="iconfont icon-gouwuche" onclick="javascript:window.location.href='http://localhost:8085/cart/cart.html';return false;"></i></p>
	</div>
</div>

	<!--main start-->
	<div class="w main">
		<div class="left">
			<div class="m" id="succeed">

				<div class="corner tl"></div>
				<div class="corner tr"></div>
				<div class="corner bl"></div>
				<div class="corner br"></div>
				<div class="success">
					<div class="success-b">
						<h3>商品已成功加入购物车！</h3>
						<span id="flashBuy" style="display: none">商品数量有限，请您尽快下单并付款！</span>
					</div>
					<span id="initCart_next_go"> <a class="btn-1"
						href="/cart/cart.html"
						id="GotoShoppingCart">去购物车结算</a> <span class="ml10">您还可以 <a
							class="ftx-05" href="javascript:history.back();">继续购物</a></span>
					</span>
				</div>
			</div>
			<!--succeed end-->

		</div>
	</div>



</body>
</html>