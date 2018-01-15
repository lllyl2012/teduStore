package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface GoodsService {
	@Deprecated
	List<Goods> getGoodsList(Integer page);
	List<Goods> getGoodsByCategory(Integer categoryId,String orderBy,Integer page,Integer pageCount);
	public Integer getGoodsPagesByCategory(Integer categoryId);
	Goods getGoodsById(Integer id);
	List<Goods> getGoodsByItemType(String itemType);
}
