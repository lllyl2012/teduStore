package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.GoodsCategoryService;
@Controller
@RequestMapping("/goods_category")
public class GoodsCategoryController extends BaseController{
	@Resource
	private GoodsCategoryService goodsCategoryService;
	
	@RequestMapping("/list.do")
	public String getGoodsCategory(Integer page,ModelMap map) {
		List<GoodsCategory> data;
		if(page==null || page<0) {
			page=1;
		}
		data = goodsCategoryService.getGoodsCategoryList(page);
		map.put("data", data);
		return "goods_category_list";
	}

	
}
