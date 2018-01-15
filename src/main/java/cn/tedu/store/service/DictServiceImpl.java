package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.entity.dict.Area;
import cn.tedu.store.entity.dict.City;
import cn.tedu.store.entity.dict.Province;
import cn.tedu.store.mapper.DictMapper;
@Service("dictService")
public class DictServiceImpl implements DictService{
	@Resource
	private DictMapper dictMapper;
	
	public List<Province> getProvinceList() {
		return dictMapper.getProvinceList();
	}

	public List<City> getCityList(String provinceCode) {
		return dictMapper.getCityList(provinceCode);
	}

	public List<Area> getAreaList(String areaCode) {
		return dictMapper.getAreaList(areaCode);
	}

	public String getProNameByProCode(String provinceCode) {
		return dictMapper.getProNameByProCode(provinceCode);
	}

	public String getCtiyNameByCityCode(String cityCode) {
		return dictMapper.getCtiyNameByCityCode(cityCode);
	}

	public String getAreaNameByAreaCode(String areaCode) {
		return dictMapper.getAreaNameByAreaCode(areaCode);
	}

}
