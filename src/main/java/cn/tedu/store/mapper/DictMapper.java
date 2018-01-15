package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.dict.Area;
import cn.tedu.store.entity.dict.City;
import cn.tedu.store.entity.dict.Province;

public interface DictMapper {
	List<Province> getProvinceList();
	List<City> getCityList(@Param("provinceCode") String provinceCode);
	List<Area> getAreaList(@Param("cityCode") String cityCode);
	String getProNameByProCode(@Param("provinceCode") String provinceCode);
	String getCtiyNameByCityCode(@Param("cityCode") String cityCode);
	String getAreaNameByAreaCode(@Param("areaCode") String areaCode);
}
