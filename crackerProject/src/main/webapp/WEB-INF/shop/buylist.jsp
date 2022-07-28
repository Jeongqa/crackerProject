<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/d84eab0825.js" crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/index/js/shop/buylist.js"></script>
<style type="text/css" src="/index/css/reset.css"></style>
<style type="text/css">

#cart_itemList {
	width: 980px;
	margin: 0 auto;
	
}

.tabs {
	text-align: center;
}	
.cartItemCount{
	float: left;
    width: 100%;
    border-top: 2px solid #0073e9;
    border-right: 2px solid #0073e9;
    border-bottom: 0;
    border-left: 2px solid #0073e9;
    padding-top: 17px;
    padding-bottom: 16px;
    background: #fff;
    font-weight: 700;
    color: #0073e9;
}


.cartTable {
	width: 100%;
	display: inline-block;
	margin-top: 30px;
	border-top: 1px solid #ddd;
    
	
}
.cartTable tr.head{
	border-bottom: 1px solid #ddd;
    display: inline-block;
    line-height: 40px;
    width: 100%;

}
.cartTable v.body{
	border-bottom: 1px solid #ddd;
	position: relative;
    display: -webkit-inline-box;
    line-height: 20px;
    font-size: 13px;
 
}

.head{
	width: 100%;
    height: 100%;
	border-bottom: 1px solid #ddd;	
	display: -webkit-inline-box;
}

.body{
	padding-top: 12px;
    padding-bottom: 12px;
    border-bottom: 1px solid #ddd;
    width: 100%;
    height: 100%;
    display: -webkit-inline-box;
    }

.cartTable th{
	
    font-size: 13px;}
    
.shop_basket{
	font-size: 24px;
	display: inline-block;
	font-weight: 900;
}
	
.shop_pree{
	display: contents;
	font-size: 13px;
}
.item-checkbox{    
	float: left;
    width: 11%;
    padding-left:14px;
}
.item-checkbox-select{
	float: left;
    width: 3%;
    padding-left:14px;
    display: flex;
}
.item-product{
	float: left;
    width: 60%;
    text-align: center;
}
.item-product-select{
	width: 60%;
    display: inline-block;
    float: left;
}

.item-price{
	float: left;
    width: 13%;
    text-align: center;
    
}
.{
	float: left;
    width: 14%;
    text-align: center;
}

.item-product-select h3{
	text-align : center;
    padding-left: 15px; 
}
.item-product-select p{

    display: flex;
    padding-left: 15px;
}


.totalSum {
	    background: chartreuse;
}

.price-area{
	border: 3px solid #ddd;
    text-align: center;
    line-height: 80px;
    font-size: large;
    word-spacing: 4.6px;
    font-weight: bold;
}

#cart-button{
	text-align: center;
    padding: 30px;
}
.cart-button-shop{

	margin: 10px;
	width: 247px;
    height: 56px;
    background: #fe7600;
    border: 1px solid #fe7600;
    font-size: 16px;
    line-height: 54px;
    color: #fff;
    font-weight: bold;
    cursor: pointer;
}
.item-product-select .minus{
	left : 0;
	right: 0;
	width: 40px;
	height: 40px;
	border-right : 1px solid #dbdbdb;
	cursor: pointer;
}

.item-product-select .plus{
	left : 0;
	right: 0;
	width: 40px;
	height: 40px;
	border-left : 1px solid #dbdbdb;
	cursor: pointer;
}

.item-product-select .input{
width: 40px;
	height: 36px;
	font-size : 18px;
	font-family : "Tahoma";
	text-align : center;
	border: 2px solid;
	padding : 0;	
}
#item-product-img{
    display: flex;
    float: left;
}

.item-price-select{
	float:left;
	width:13%;
	text-align: center;
	position: relative;
    line-height: 145px;
	
}

.itemimg{
 width: 100px;
 height: 50px;
 margin-left: 6px;
 
}

.steps {
	float: right;
    padding-right: 5px;
    margin-top: 20px;
    line-height: 50px;
    font-size: 15px;
    color: #bbb;
    font-weight: 700;
}
.steps em{
    font-weight: 700;
    color: #346aff;	
}
</style>


</head>
<body>
	<input type = "hidden" value="${memEmail}" id = "memberemail" name = "memberemail" >
	<div id="cartViewList">

	<section id="cart_itemList" class="cart_itemList">        
        <section class="carttitle">
            <h1 class="shop_basket">주문목록</h1>
            <p class="shop_pree">구매된 상품</p>

     		 <span class="steps">
				장바구니&gt;주문결제 &gt;<em>주문완료</em>
			</span>
		 </section>
	   	 <div id="cartContent">
	       		<div class="cartTable" >
					<div class="head" style="line-height: 48px;">
					    <div class="item-checkbox" >
					    	주문날짜</div>
					    <div class="item-product" >상품정보</div>
					    <div class = "item-price">상품갯수</div>
					    <div class="item-price">상품금액</div>
					</div>
					    <!-- for문 돌리기 -->  
					 <div class = "test">
					
	        		</div>
	        	</div>
     	  </div>
     </section>
    </div> 


</body>
</html>