package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;

public interface GoodsCategoryService {
	List<GoodsCategory> getGoodsCategoryList(Integer page);
	List<GoodsCategory> getGoodsCategoryListByParentId(Integer page,Long categoryId,Integer pageCount);
	GoodsCategory getGoodsCategoryById(Integer categoryId);
}
