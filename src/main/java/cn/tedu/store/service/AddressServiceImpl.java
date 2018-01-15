package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.DictMapper;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
	@Resource
	AddressMapper addressMapper;
	@Resource
	DictMapper dictMapper;

	public void addAddress(Address address) {
		String province = dictMapper.getProNameByProCode(address.getRecvProvince());
		String city = dictMapper.getCtiyNameByCityCode(address.getRecvCity());
		String area = dictMapper.getAreaNameByAreaCode(address.getRecvArea());
		address.setRecvDistrict(province + city + area);
		addressMapper.addAddress(address);
	}

	public List<Address> findAddressByUid(Integer id) {
		return addressMapper.findByUid(id);
	}

	public void setDefault(Integer id) {
		addressMapper.setAllNotDefault();
		addressMapper.setDefault(id);
	}

	public void removeAddress(Integer id,Integer uid) {
		addressMapper.deleteAddress(id,uid);
	}

	public Address findById(Integer id) {
		return addressMapper.findById(id);
	}

	public Integer selectAddressCount(Integer uid) {
		return addressMapper.selectAddressCount(uid);
	}

	public Address findDefaultByUid(Integer uid) {
		return addressMapper.findDefaultByUid(uid);
	}

}
