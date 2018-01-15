package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;

@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService{
	@Resource
	private GoodsCategoryMapper goodsCategoryMapper; 
	
	public List<GoodsCategory> getGoodsCategoryList(Integer page) {
		return goodsCategoryMapper.getGoodsCategoryList(page);
	}

	public List<GoodsCategory> getGoodsCategoryListByParentId(Integer page, Long parentId, Integer pageCount) {
		Integer pId = (int)(parentId.longValue());
		if(pageCount!=null) {
			if(pageCount<-1||pageCount>20) {
				pageCount=20;
			}
		}
		Integer offset = 0;
		return goodsCategoryMapper.getGoodsCategoryListByParentId(offset, pId, pageCount);
	}

	public GoodsCategory getGoodsCategoryById(Integer categoryId) {
		return goodsCategoryMapper.getGoodsCategoryById(categoryId);
	}


}
