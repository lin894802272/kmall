<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0"> 
   <meta name="format-detection" content="telephone=no">  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
   <meta name="format-detection" content="telephone=no">
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
   <link rel="stylesheet" href="/css/base.css">
   <link rel="stylesheet" href="/js/cart.js">

    <link href="/css/public.css" rel="stylesheet"/>
    <link href="/css/cz.css" rel="stylesheet"/>
    <!--引入iconfont-->
    <link rel="stylesheet" href="/css/iconfont.css">
    <!--引入swiper-->
    <link rel="stylesheet" href="/css/swiper.min.css">

   <link href="/css/purchase.2012.css?v=201410141639" rel="stylesheet" type="text/css">
   <title>我的购物车 - Kamll商城</title>

    <script type="text/javascript">
        $(function (){
            alert("我进来了");
            $("#toSettlement").click(function(){
                //alert($(".checkbox:checked").length);
                //i脚标   n当前循环时对象,对象是一个dom对象
                alert("我点击了");
                var param = "";
                $.each($(".checkbox:checked"),function(i,n){
                    //alert($(n).val());
                    param+="id="+$(n).val();
                    if(i<$(".checkbox:checked").length-1){
                        param+="&";
                    }
                });
                //alert(param);
                location.href=$(this).attr("href")+"?"+param;
                return false;
            });

        });
    </script>

    <script>
   	var pageConfig  = {};
   </script>
    <style>
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
<body> 
<!--shortcut start-->
<div class="cz_top">
    <h1><a href="http://localhost:8082"><img src="/img/top/logo.png" alt=""></a></h1>
    <div class="top_sign">
        <p><i class="iconfont icon-denglu" onclick="javascript:window.location.href='http://localhost:8082/user/showLogin';return false;"></i></p>
        <p><i class="iconfont icon-gouwuche" onclick="javascript:window.location.href='http://localhost:8085/cart/cart.html';return false;"></i></p>
    </div>
</div>


<!--shortcut end-->
<div class="w w1 header clearfix">
	<div id="logo"><a href="/"><img clstag="clickcart|keycount|xincart|logo" src="http://pz34oktwy.bkt.clouddn.com/Kmalllogo128px.png" title="返回Kamll商城首页" alt="返回Kamll商城首页"></a></div>
    <div class="language"><a href="javascript:void(0);" onclick="toEnCart()"></a></div>
	<div class="progress clearfix">
		<ul class="progress-1">
			<li class="step-1"><b></b>1.我的购物车</li>
			<li class="step-2"><b></b>2.填写核对订单信息</li>
			<li class="step-3">3.成功提交订单</li>
		</ul>
	</div>
</div>
<div class="w cart">
	<div class="cart-hd group">
		<h2>我的购物车</h2>
	</div>
	<div id="show">
	
<div class="cart-frame">
    <div class="tl"></div>
    <div class="tr"></div>
</div>
<div class="cart-inner">
    <div class="cart-thead clearfix">
        <div class="column t-checkbox form"><input data-cart="toggle-cb" name="toggle-checkboxes" id="toggle-checkboxes_up" type="checkbox" checked="" value=""><label for="toggle-checkboxes_up">全选</label></div>
        <div class="column t-goods">商品</div>
        <div class="column t-price">Kamll价</div>
        <div class="column t-promotion">优惠</div>
        <div class="column t-inventory">库存</div>
        <div class="column t-quantity">数量</div>
        <div class="column t-action">操作</div>
    </div>
    <div id="product-list" class="cart-tbody">
        <!-- ************************商品开始********************* -->
        <c:set var="totalPrice" value="0"></c:set>
        <c:forEach items="${cartList}" var="cart">
        	<c:set var="totalPrice"  value="${ totalPrice + (cart.price * cart.num)}"/>
	        <div id="product_11345721" data-bind="rowid:1" class="item item_selected ">
		        <div class="item_form clearfix">
		            <div class="cell p-checkbox"><input data-bind="cbid:1" class="checkbox" type="checkbox" name="checkItem" checked="" value="${cart.id }"></div>
		            <div class="cell p-goods">
		                <div class="p-img">
		                	<a href="/item/${cart.id }.html" target="_blank">
		                		<img clstag="clickcart|keycount|xincart|p-imglistcart" src="${cart.images[0]}" alt="${cart.title}" width="52" height="52">
		                	</a>
		                </div>    
		                <div class="p-name">
		                	<a href="/item/${cart.id }.html" clstag="clickcart|keycount|xincart|productnamelink" target="_blank">${cart.title}</a>
		                	<span class="promise411 promise411_11345721" id="promise411_11345721"></span>
		                </div>    
		            </div>
		            <div class="cell p-price"><span class="price">¥<fmt:formatNumber groupingUsed="false" value="${cart.price / 100}" maxFractionDigits="2" minFractionDigits="2"/> </span></div>
		            <div class="cell p-promotion">
		            </div>
		            <div class="cell p-inventory stock-11345721">有货</div>
		            <div class="cell p-quantity" for-stock="for-stock-11345721">
		                <div class="quantity-form" data-bind="">
		                    <a href="javascript:void(0);" class="decrement" clstag="clickcart|keycount|xincart|diminish1" id="decrement">-</a>
		                    <input type="text" class="quantity-text" itemPrice="${cart.price}" itemId="${cart.id}" value="${cart.num }" id="changeQuantity-11345721-1-1-0">
		                    <a href="javascript:void(0);" class="increment" clstag="clickcart|keycount|xincart|add1" id="increment">+</a>
		                </div>
		            </div>
		            <div class="cell p-remove"><a id="remove-11345721-1" data-more="removed-87.20-1" clstag="clickcart|keycount|xincart|btndel318558" class="cart-remove mycart_remove" href="/cart/delete/${cart.id}.action">删除</a>
		            </div>
		        </div>
	        </div> 
        </c:forEach>
        
    </div><!-- product-list结束 -->
          <div class="cart-toolbar clearfix">
            <div class="total fr">
                <p><span class="totalSkuPrice">¥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></span>总计：</p>
                <p><span id="totalRePrice">- ¥0.00</span>优惠：</p>
            </div>
            <div class="amout fr"><span id="selectedCount">1</span> 件商品</div>
        </div>
        <div class="ui-ceilinglamp-1" style="width: 988px; height: 49px;"><div class="cart-dibu ui-ceilinglamp-current" style="width: 988px; height: 49px;">
          <div class="control fdibu fdibucurrent">
              <span class="column t-checkbox form">
                  <input data-cart="toggle-cb" name="toggle-checkboxes" id="toggle-checkboxes_down" type="checkbox" checked="" value="" class="jdcheckbox">
                  <label for="toggle-checkboxes_down">
                          全选
                  </label>
              </span>
              <span class="delete">
                  <b>
                  </b>
                  <a href="javascript:void(0);" clstag="clickcart|keycount|xincart|clearcartlink" id="remove-batch">
                          删除选中的商品
                  </a>
              </span>
              <span class="shopping">
                  <b>
                  </b>
                  <a href="/" target="_blank" clstag="clickcart|keycount|xincart|coudanlink" id="continue">继续购物</a>
              </span>
          </div>
          <div class="cart-total-2014">
              <div class="cart-button">
                  <span class="check-comm-btns" id="checkout-jd">
                      <a class="checkout" href="http://localhost:8086/order/order-cart.html" clstag="clickcart|keycount|xincart|gotoOrderInfo" id="toSettlement">去结算<b></b></a>
                  </span>
                  <span class="combine-btns" style="display:none">
                        <span class="fore1" style="display: none;">
                          <a href="" class="combine-btn">不支持合并付款</a>
                      </span>
                      <span class="fore2 hide" style="display: inline;">
                          <a href="javascript:goToOverseaOrder();" class="checkout-jdInt">去Kamll国际结算<b></b></a>
                          <a href="javascript:goToOrder();" class="checkout-jd">去Kamll结算<b></b></a>
                      </span>
                  </span>
              </div>
              <div class="total fr">
                  总计（不含运费）：
                  <span class="totalSkuPrice">¥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></span>
              </div>
          </div>
      </div></div>
</div><!-- cart-inner结束 -->
</div>
</div>
<!--推荐位html修改处-->


<script type="text/javascript" src="/js/base-v1.js"></script>
<!-- footer start -->


<!-- footer end -->

<!-- 购物车相关业务 -->
<script type="text/javascript" src="/js/cart.js"></script>
<script type="text/javascript" src="/js/jquery.price_format.2.0.min.js"></script>

</html>