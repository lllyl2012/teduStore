package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.dict.Area;
import cn.tedu.store.entity.dict.City;
import cn.tedu.store.entity.dict.Province;

public interface DictService {
	List<Province> getProvinceList();
	List<City> getCityList(String provinceCode);
	List<Area> getAreaList(String areaCode);
	String getProNameByProCode(String provinceCode);
	String getCtiyNameByCityCode(String cityCode);
	String getAreaNameByAreaCode(String areaCode);
}
