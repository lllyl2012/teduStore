package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{
	@Resource
	private GoodsMapper goodsMapper;
	
	@Deprecated
	public List<Goods> getGoodsList(Integer page) {
		return goodsMapper.getGoodsList((page-1)*25);
	}

	public List<Goods> getGoodsByCategory(Integer categoryId,String orderBy, Integer page,Integer pageCount) {
		if(pageCount==null||pageCount<0||pageCount>20) {
			pageCount=20;
		}
		if(page==null||page<=0) {
			page = 1;
		}
		Integer offset = (page - 1)*pageCount;
		return goodsMapper.getGoodsByCategory(offset, orderBy,categoryId, pageCount);
	}
	
	public Integer getGoodsPagesByCategory(Integer categoryId) {
		Integer goodsCount = goodsMapper.getGoodsPageNumByCategory(categoryId);
		Integer pageNum = goodsCount/12;
		if(goodsCount%12!=0) {
			pageNum++;
		}
		return pageNum;
	}

	public Goods getGoodsById(Integer id) {
	  return	goodsMapper.getGoodsById(id);
	}

	public List<Goods> getGoodsByItemType(String itemType) {
		return goodsMapper.getGoodsByItemType(itemType);
	}
}
