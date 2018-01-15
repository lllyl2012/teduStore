package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface GoodsMapper {
	@Deprecated
	List<Goods> getGoodsList(@Param("offset") Integer offset);
	/**
	 * 根据类型获得商品
	 * @param offset
	 * @param orderBy
	 * @param categoryId
	 * @param pageCount
	 * @return
	 */
	List<Goods> getGoodsByCategory(@Param("offset") Integer offset,@Param("orderBy") String orderBy,@Param("categoryId") Integer categoryId,@Param("pageCount") Integer pageCount);
	/**
	 * 根据类型获得商品页数
	 * @param categoryId
	 * @return
	 */
	Integer getGoodsPageNumByCategory(@Param("categoryId") Integer categoryId);
	/**
	 * 根据id获得商品
	 * @param id
	 * @return
	 */
	Goods getGoodsById(@Param("id") Integer id);
	/**
	 * 获得该商品的不同款样式
	 * @param itemType
	 * @return
	 */
	List<Goods> getGoodsByItemType(@Param("itemType") String itemType);
}
