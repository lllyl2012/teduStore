package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.dict.Area;
import cn.tedu.store.entity.dict.City;
import cn.tedu.store.entity.dict.Province;
import cn.tedu.store.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController{
	@Resource
	private DictService dictService;
	
	@RequestMapping("/get_province_list.do")
	@ResponseBody
	public ResponseResult<List<Province>> getProvincesList(){
		List<Province> list = dictService.getProvinceList();
		ResponseResult<List<Province>> rr = new ResponseResult<List<Province>>();
		rr.setState(ResponseResult.STATE_OK);
		rr.setMessage("省份");
		rr.setData(list);
		return rr;
	}
	
	@RequestMapping("/get_cities_list.do")
	@ResponseBody
	public ResponseResult<List<City>> getCitiesList(String provinceCode){
		List<City> list = dictService.getCityList(provinceCode);
		ResponseResult<List<City>> rr = new ResponseResult<List<City>>();
		rr.setState(ResponseResult.STATE_OK);
		rr.setMessage("城市");
		rr.setData(list);
		return rr;
	}
	@RequestMapping("/get_areas_list.do")
	@ResponseBody
	public ResponseResult<List<Area>> getAreasList(String cityCode){
		List<Area> list = dictService.getAreaList(cityCode);
		ResponseResult<List<Area>> rr = new ResponseResult<List<Area>>();
		rr.setState(ResponseResult.STATE_OK);
		rr.setMessage("地区");
		rr.setData(list);
		return rr;
	}
}
