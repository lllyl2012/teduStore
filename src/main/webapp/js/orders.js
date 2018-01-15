/**
 * Created by yy on 2016/12/15.
 */

$("#leftsidebar_box dt").css({"background-color":" #0AA1ED"});
$(function(){
		showMenuItem();
    $("#leftsidebar_box dt").click(function(){
    	//为各族选项分类标题（4个）设置背景颜色
        $("#leftsidebar_box dt").css({"background-color":"#0AA1ED"});
//        $(this).css({"background-color": "#0AA1ED"});
        $(this).parent().find('dd').removeClass("menu_chioce");
        $("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
        $(this).parent().find('img').attr("src","../images/myOrder/myOrder1.png");
        $(".menu_chioce").slideUp();
        $(this).parent().find('dd').slideToggle();
        $(this).parent().find('dd').addClass("menu_chioce");
        $(this).parent().siblings().children('dd').slideUp();
    });
})
//分页部分
$(".tcdPageCode").createPage({
    pageCount:6,
    current:1,
    backFn:function(p){

          }
});


