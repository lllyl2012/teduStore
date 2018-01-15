package cn.tedu.store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.AddressService;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	@Resource
	AddressService addressService;
	/**
	 * 添加新地址
	 * @param address
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/handle_add_address.do")
	@ResponseBody
	public ResponseResult<Void> handleAddress(Address address,HttpSession session) {
		ResponseResult<Void> rr = new ResponseResult<Void>();
		Integer id = address.getId();
//		Integer uid = address.getUid();
		Integer uid = this.getUidFromSession(session);
		try {
			if(id!=null) {
				addressService.removeAddress(id,uid);
			}
			address.setUid(uid);
			addressService.addAddress(address);
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("成功添加或修改");
		} catch (Exception e) {
			e.printStackTrace();
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("添加失败");
		}
		int num = addressService.selectAddressCount(address.getUid());
		if(num==1) {
			addressService.setDefault(address.getId());
		}
		return rr;
	}

	/**
	 * 设置默认地址
	 * @param id
	 * @return
	 */
	@RequestMapping("/setDefault.do")
	public ResponseResult<Void> setDefault(Integer id) {
		ResponseResult<Void> rr = new ResponseResult<Void>();
		try {
			addressService.setDefault(id);
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("修改成功");
		} catch (Exception e) {
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("未知错误，修改失败");
		}
		return rr;
	}
	
	/**
	 * 删除地址
	 * @param id
	 * @return
	 */
	@RequestMapping("/removeAddress.do")
	public String deleteAddress(Integer id,HttpSession session) {
		Address address = addressService.findById(id);
		Integer uid = this.getUidFromSession(session);
		addressService.removeAddress(id,uid);
		System.out.println(address);
		if("1".equals(address.getIsDefault())) {
			try {
				addressService.setDefault(addressService.findAddressByUid(uid).get(0).getId());
			} catch (Exception e) {
			}
		}
		//设置默认
		return "redirect:../user/address.do";
	}
	
	/**
	 * 获得地址信息
	 */
	@RequestMapping("/showAddress.do")
	@ResponseBody
	public ResponseResult<Address> getAddress(Integer id){
		ResponseResult<Address> rr = new ResponseResult<Address>();
		try {
			Address address =  addressService.findById(id);
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("获得地址成功");
			rr.setData(address);
		} catch (Exception e) {
			e.printStackTrace();
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("未知错误");
		}
		return rr;
	}
}
