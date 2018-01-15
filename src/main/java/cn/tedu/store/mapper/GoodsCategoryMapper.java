package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;

public interface GoodsCategoryMapper {
	List<GoodsCategory> getGoodsCategoryList(@Param("offset") Integer offset);
	List<GoodsCategory> getGoodsCategoryListByParentId(@Param("offset") Integer offset,@Param("parentId") Integer parentId,@Param("pageCount") Integer pageCount);
	GoodsCategory getGoodsCategoryById(@Param("categoryId") Integer categoryId);
}
