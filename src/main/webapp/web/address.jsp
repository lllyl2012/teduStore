<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
     <c:set var="base" scope="request" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="../css/orders.css" rel="stylesheet"/>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
    <link href="../css/common.css" rel="stylesheet" />
</head>
<body>
<!-- 页面顶部-->
<c:import url="header.jsp"></c:import>
<!-- nav主导航-->
<nav id="nav">
    <ul>
        <li><a href="${base}/main/index.do" class="acti">首页</a></li>
        <li><a href="index.html#computer" >电脑办公</a></li>
        <li><a href="index.html#stationery" >办公文具</a></li>
    </ul>
</nav>
<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="">首页<span>&gt;</span>个人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
    <c:import url="left_side_menu.jsp"></c:import>
   <!-- 右边栏-->
    <div class="rightsidebar_box rt">	
        <!--标题栏-->
        <div class="rs_header">
            <span class="address_title">收获地址管理</span>
        </div>
      
        <!--收货人信息填写栏-->
        <div class="rs_content">
        	
            <!--已有地址栏-->
            <div class="address_information_manage">
                <div class="aim_title">
                    <span class="dzmc dzmc_title">地址名称</span><span class="dzxm dzxm_title">姓名</span><span class="dzxq dzxq_title">地址详情</span><span class="lxdh lxdh_title">联系电话</span><span class="operation operation_title">操作</span>
                </div>
                <c:forEach var="i" items="${allAddress}">
            
												<c:choose>

													<c:when test="${i.isDefault eq '1'}">
															<div class="aim_content_one aim_active">
							                    <span class="dzmc dzmc_active">${i.recvName}</span>
							                    <span class="dzxm dzxm_normal">${i.recvPerson}</span>
							                    <span class="dzxq dzxq_normal">${i.recvDistrict}${i.recvAddr}</span>
							                    <span class="lxdh lxdh_normal">${i.recvTel}</span>
							                    <span class="operation operation_normal">
							                    	<span onclick="showMask(${i.id})" class="aco_change">修改</span>|<span onclick="removeAddress(${i.id})" class="aco_delete">删除</span>
							                    </span>
							                    <span class="swmr swmr_normal"></span>
							                </div>
												  </c:when>
													<c:otherwise>
															<div class="aim_content_three">
							                    <span class="dzmc dzmc_normal">${i.recvName}</span>
							                    <span class="dzxm dzxm_normal">${i.recvPerson}</span>
							                    <span class="dzxq dzxq_normal">${i.recvDistrict}${i.recvAddr}</span>
							                    <span class="lxdh lxdh_normal">${i.recvTel}</span>
							                    <span class="operation operation_normal">
							                    	<span onclick="showMask(${i.id})" class="aco_change">修改</span>|<span onclick="removeAddress(${i.id})" class="aco_delete">删除</span>
							                    </span>
							                    <span onclick="setDefaultAdd(this.title)" title="${i.id}" id="set_default" class="swmr swmr_normal">设为默认</span>
							                </div>   
													</c:otherwise>
												</c:choose>
								</c:forEach>
            </div>
        			<!-- 新增收获人地址 -->
        		<div>
        			<a href="#" onclick="showMask(0)" class="button_classic">新增收获人地址 </a>
        		</div>
        </div>
    </div>
</div>

<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../images/footer/icon1.png" alt=""/>

        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../images/footer/icon2.png" alt=""/>

        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../images/footer/icon3.png" alt=""/>

        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../images/footer/icon4.png" alt=""/>

        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<div class="foot_bj">
    <div id="foot">
        <div class="lf">
             <p class="footer1"><img src="../images/footer/logo.png" alt="" class=" footLogo"/></p>
             <p class="footer2"><img src="../images/footer/footerFont.png" alt=""/></p>
        </div>
        <div class="foot_left lf">
            <ul>
                <li><a href="#"><h3>买家帮助</h3></a></li>
                <li><a href="#">新手指南</a></li>
                <li><a href="#">服务保障</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>商家帮助</h3></a></li>
                <li><a href="#">商家入驻</a></li>
                <li><a href="#">商家后台</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>关于我们</h3></a></li>
                <li><a href="#">关于达内</a></li>
                <li><a href="#">联系我们</a></li>
                <li>
                    <img src="../images/footer/wechat.png" alt=""/>
                    <img src="../images/footer/sinablog.png" alt=""/>
                </li>
            </ul>
        </div>
        <div class="service">
            <p>学子商城客户端</p>
            <img src="../images/footer/ios.png" class="lf">
            <img src="../images/footer/android.png" alt="" class="lf"/>
        </div>
        <div class="download">
            <img src="../images/footer/erweima.png">
        </div>
		<!-- 页面底部-备案号 #footer -->
        <div class="record">
            &copy;2017 达内集团有限公司 版权所有 京ICP证xxxxxxxxxxx
        </div>
    </div>
</div>
  <!-- 遮罩和内容：开始 -->
        <div id="mark"></div>	
        <div id="popup_content" >
          <div class="content_wrapper">
          <h3 id="popup_title"></h3><hr>
			        <form id="sendAddressForm" method="post" action="${base}/address/handle_add_address.do">
			        <input type="hidden" name="id" id="addressId" value="${id}">
				            <!--收货人姓名-->
				            <div class="recipients">
				                <span class="red">*</span><span class="kuan">收货人：</span><input type="text" name="recvPerson" id="receiverName"/>
				            </div>
				            <!--收货人所在城市等信息-->
				            <div data-toggle="distpicker2" class="address_content">
								 <span class="red">*</span>
								 <span class="kuan">省份：</span>
								 <select name="recvProvince" onchange="getCityList(this.value)"  id="province">
								 </select>
								  城市：
								  <select name="recvCity" onchange="getAreasList(this.value)"  id="city">
								  	<option value="0">----请选择----</option>
								  </select>
								  区/县：
								  <select name="recvArea"  id="area">
								  	<option value="0">----请选择----</option>
								  </select>
							</div> 
				            
				            
				            <!--收货人详细地址-->
				            <div class="address_particular">
				                <span class="red">*</span><span class="kuan">详细地址：</span><textarea name="recvAddr" id="receiverAddress" cols="60" rows="3" placeholder="建议您如实填写详细收货地址"></textarea>
				            </div>
				            <!--收货人地址-->
				            <div class="address_tel">
				                <span class="red">*</span><span class="kuan">手机号码：</span><input type="tel" id="receiverMobile" name="recvTel"/>固定电话：<input type="text" name="recvPhone" id="receiverPhone"/>
				            </div>
				            <!--邮政编码-->
				            <div class="address_postcode">
				                <span class="red">&nbsp;</span class="kuan"><span>邮政编码：</span>&nbsp;<input type="text" id="recvDddrCode" name="recvDddrCode"/>
				            </div>
				            <!--地址名称-->
				            <div class="address_name">
				                <span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input type="text" id="addressName" name="recvName"/>如：<span class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
				            </div>
				            <!--保存收货人信息-->
				            <div class="buttons"> 
	           					 	<a href="#" onclick="saveAddress()" class="button_classic">保存收货人信息</a>
	            					<a href="#" onclick="hideMask()" class="button_cancel">取消</a> 
				            </div>
			    		</form>
			 		</div>
     		</div>
        <!-- 遮罩和内容：结束 -->
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript" src="../js/distpicker.data.js"></script>
<script type="text/javascript" src="../js/distpicker.js"></script>
<script type="text/javascript" src="../js/personal.js"></script>
<script type="text/javascript">

	$(".lxdh_normal").each(function(i,e) {
		var phone = $(e).html();
		$(e).html(changePhone(phone));
	});
	
	function showMask(id){
// 		getProvinceList();
		$("#sendAddressForm")[0].reset();
// 		$("#sendAddressForm :input").val("");
// 		$("select").val("");
// 		$("textarea").text("");
		//内容长宽
		var contentWidth = 720;
		var contentHeight = 450;
		//更改样式
		$("#mark").css({
			"width":$(document).width(),
			"height":$(document).height()
				});
		$("#popup_content").css({
					"width":contentWidth,
					"height":contentHeight,
			"left":($(window).width()-820)/2,
			"top":($(window).height()-420)/2
				});
		//处理标题文字
		var title = id==0?"新增收获人信息":"编辑收货人信息";
		$("#popup_title").text(title);
		//显示修改地址框
		$("#mark").show();
		$("#popup_content").show();
		if(id!=0){
			showAddress(id);
		}else{
			getProvinceList(null,null,null);
		}
	}
	
	function hideMask(){
		$("#mark").hide();
		$("#popup_content").hide();
	}
	
	/*获取省份城市区域*/
	function getProvinceList(provinceCode,cityCode,areaCode){
		$.ajax({
			"url":"${base}/dict/get_province_list.do",
			"type":"get",
			"dataType":"json",
			"success":function(json){
				$("#province").empty();
				$("#city").empty();
				$("#area").empty();
				var op;
				for(var i=0;i<json.data.length;i++){
					op = "<option value=" + json.data[i].provinceCode + ">"
					   +json.data[i].provinceName
					   + "</option>";
					$("#province").append(op);
				}
				if(provinceCode!=null){
					$("#province").val(provinceCode);
				}
				getCityList(provinceCode,cityCode,areaCode);
			}
		});
	}
	
	function getCityList(provinceCode,cityCode,areaCode){
		$.ajax({
			"url":"${base}/dict/get_cities_list.do",
			"data":"provinceCode="+provinceCode,
			"type":"get",
			"dataType":"json",
			"success":function(json){
				$("#city").empty();
				$("#area").empty();
				var op;
				for(var i=0;i<json.data.length;i++){
// 					op = "<option value=" + json.data[i].cityCode + ">"
// 					   +json.data[i].cityName
// 					   + "</option>";
						op = $("<option>").val(json.data[i].cityCode).text(json.data[i].cityName)
					$("#city").append(op);
				}
				if(cityCode!=null){
					$("#city").val(cityCode);
				}
				getAreasList(cityCode,areaCode);
			}
		});
	}
	function getAreasList(cityCode,areaCode){
		$.ajax({
			"url":"${base}/dict/get_areas_list.do",
			"data":"cityCode="+cityCode,
			"type":"get",
			"dataType":"json",
			"success":function(json){
				$("#area").empty();
				var op;
				for(var i=0;i<json.data.length;i++){
					op = "<option value=" + json.data[i].areaCode + ">"
					   +json.data[i].areaName
					   + "</option>";
					$("#area").append(op);
				}
				if(areaCode!=null){
					console.log(areaCode);
					$("#area").val(areaCode);
				}
			}
		});
	}

	function saveAddress(){
		var data = $("#sendAddressForm").serialize();
		$.ajax({
			"url":"${base}/address/handle_add_address.do",
			"data":data,
			"datatype":"json",
			"type":"post",
			"success":function(obj){
				hideMask();
				if(obj.state=="1"){
					alert(obj.message);
					window.location = "${base}/user/address.do";
				}
			}
		});
	}
	
	function setDefaultAdd(t,event){
		$.ajax({
			"url":"${base}/address/setDefault.do",
			"data":"id="+t,
			"datatype":"json",
			"type":"get",
			"success":function(json){
				console.log("aaaa");
				if(json.state == "1"){
					alert("修改默认地址成功");
					window.location = "${base}/user/address.do";
				}else{
					alert("发生异常，修改失败");
					hideMask();
				}
			}
		});
	}
	
	function removeAddress(id){
		var flag = confirm("确定要删除该地址吗");
		if(flag){
			window.location = "${base}/address/removeAddress.do?id="+id;
		}
	}
	
	function showAddress(id){
		console.log("id:"+id);
		$.ajax({
			"url":"${base}/address/showAddress.do",
			"data":"id="+id,
			"datatype":"json",
			"type":"post",
			"success":function(json){	
				console.log(json);
				$("#addressId").val(json.data.id);
				$("#receiverName").val(json.data.recvPerson);
// 				$("#province").val(json.data.recvProvince);
// 				$("#city").val(json.data.recvCity);
// 				$("#area").val(json.data.recvArea);
				$("#receiverAddress").val(json.data.recvAddr);
				$("#receiverMobile").val(json.data.recvTel);
				$("#receiverPhone").val(json.data.recvPhone);
				$("#recvDddrCode").val(json.data.recvDddrCode);
				$("#addressName").val(json.data.recvName);
				
				getProvinceList(json.data.recvProvince,json.data.recvCity,json.data.recvArea);
			}
		});
	}
	
	$(function(){
		var className = '.address';
		showMenuItem(className);
	});
</script>
</html>