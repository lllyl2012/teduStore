package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.GoodsCategoryService;
import cn.tedu.store.service.GoodsService;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController{
	@Resource
	private GoodsService goodsService;
	@Resource
	private GoodsCategoryService goodsCategoryService;
	/**
	 * 登录主页
	 * @return
	 */
	@RequestMapping("/index.do")
	public String showIndex(HttpSession session,ModelMap map) {
//		Integer categoryId = 163;
//		Integer page = 1;
//		Integer pageCount = 3;
		List<Goods> computerList = goodsService.getGoodsByCategory(163,null,1, 3);
		map.put("computerList", computerList);
		
		List<GoodsCategory> categoryList = goodsCategoryService.getGoodsCategoryListByParentId( 0,161l, 3);
		map.put("categoryList", categoryList);
		int i=0;
		List<List<GoodsCategory>> categorys = new ArrayList<List<GoodsCategory>>();
		for (GoodsCategory cat : categoryList) {
			categorys.add(
					goodsCategoryService.getGoodsCategoryListByParentId( 0,cat.getId(), 50));
			i++;
		}
		map.put("subCategory", categorys);
		
		return "index";
	}
}
