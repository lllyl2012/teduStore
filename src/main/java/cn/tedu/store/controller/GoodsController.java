package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.GoodsCategoryService;
import cn.tedu.store.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource
	private GoodsService goodsService;
	@Resource
	private GoodsCategoryService goodsCategoryService;
	/**
	 * 获得商品列表
	 * @param page
	 * @param map
	 * @return
	 */
	@RequestMapping("/list.do")
	public String getGoods(@RequestParam("id") Integer categoryId,Integer page,ModelMap map) {
		Integer pageNum = goodsService.getGoodsPagesByCategory(categoryId);
		if(pageNum==0) {
			return "search";
		}
		List<Goods> data;
		if(page==null) {
			page = 1;
		}
		//获得商品数据
		data = goodsService.getGoodsByCategory(categoryId, null, page,12);
		map.put("data", data);
		
		Integer before = page-1;
		Integer after = page+1;
		if(before==0) {
			before=1;
		}
		if(after>pageNum) {
			after=pageNum;
		}
		map.put("page", page);
		map.put("before", before);
		map.put("after", after);
		map.put("pageNum", pageNum);
		map.put("categoryId", categoryId);
		return "goods_list";
	}
	
	
	/**
	 * 查找商品细节
	 */
	@RequestMapping("/detail.do")
	public String getGoodsDetail(Integer id,ModelMap map) {
		Goods goods1 = goodsService.getGoodsById(id);
		String forward = "product_details";
		if(goods1==null) {
			map.put("message", "没有匹配的商品信息");
			return forward;
		}
		//获得该商品所有同类商品
		String itemType = goods1.getItemType();
		List<Goods> list = goodsService.getGoodsByItemType(itemType);
		map.put("goods", goods1);
		map.put("goodsList", list);
		
		//获得二级三级分类
		long categoryId = goods1.getCategoryId();
		Integer cateId3 = (int)categoryId;
		GoodsCategory goodsCategoryThird = goodsCategoryService.getGoodsCategoryById(cateId3);
		long categoryId2 = goodsCategoryThird.getParentId();
		Integer cateId2 = (int)categoryId2;
		GoodsCategory goodsCategorySecond = goodsCategoryService.getGoodsCategoryById(cateId2);
		long categoryId1 = goodsCategoryThird.getParentId();
		Integer cateId1 = (int)categoryId1;
		GoodsCategory goodsCategoryOne = goodsCategoryService.getGoodsCategoryById(cateId1);
		map.put("goodsCategoryThird", goodsCategoryThird);
		map.put("goodsCategorySecond", goodsCategorySecond);
		map.put("goodsCategoryOne", goodsCategoryOne);
		return forward;
	}
}
