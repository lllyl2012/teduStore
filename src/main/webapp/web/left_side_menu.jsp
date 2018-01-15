<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="leftsidebar_box" class="lf">
        <div class="line"></div>
        <dl class="my_order">
            <dt >我的订单
                <img src="../images/myOrder/myOrder2.png">
            </dt>
            <dd class="first_dd"><a href="#">全部订单</a></dd>
            <dd>
                <a href="#">
                    待付款
                    <span><!--待付款数量--></span>
                </a>
            </dd>
            <dd>
                <a href="#">
                    待收货
                    <span><!--待收货数量-->1</span>
                </a>
            </dd>
            <dd>
                <a href="#">
                    待评价<span><!--待评价数量--></span>
                </a>
            </dd>
            <dd>
                <a href="#">退货退款</a>
            </dd>
        </dl>

        <dl class="footMark">
            <dt >我的优惠卷<img src="../images/myOrder/myOrder1.png"></dt>
        </dl>

        <dl class="address">
                <dt>收货地址<img src="../images/myOrder/myOrder1.png"></dt>
				<dd><a href="${base}/user/address.do">地址管理</a></dd>
            </dl>
            <dl class="count_managment">
                <dt >帐号管理<img src="../images/myOrder/myOrder1.png"></dt>
                <dd class="first_dd"><a href="${base}/user/user_center.do">我的信息</a></dd>
                <dd><a href="${base}/user/user_password.do">安全管理</a></dd>
            </dl>
    </div>
    <script>
    	function showMenuItem(className){
    			$("#leftsidebar_box dd").hide();
    			$("#leftsidebar_box "+className+" dd").show();
    			$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
    			$("#leftsidebar_box "+className+" img").attr("src","../images/myOrder/myOrder1.png");
//     		var path = window.location.pathname;
//     		var lastPath = path.substring(path.lastIndexOf("/")+1);
//     		if(lastPath.indexOf('address')!='-1'){
//     			$("#leftsidebar_box dd").hide();

//     			$("#leftsidebar_box .address dd").show();
    		
//     		}else if(lastPath.indexOf('user')!=-1){
//     			$("#leftsidebar_box dd").hide();
//     			$("#leftsidebar_box .count_managment dd").show();
//     			}	
// 			$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
// 			$(this).parent().find('img').attr("src","../images/myOrder/myOrder1.png");
    		}
    </script>