package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

public interface AddressService {
	/**
	 * 增加地址
	 * @param address
	 */
	void addAddress(Address address);
	/**
	 * 根据id找地址
	 * @param id
	 * @return
	 */
	List<Address>  findAddressByUid(Integer id);
	/**
	 * 设置默认地址
	 * @param id
	 */
	void setDefault(Integer id);
	/**
	 * 删除地址
	 */
	void removeAddress(Integer id,Integer uid);
	/**
	 * 根据id查找地址
	 */
	Address findById(Integer id);
	/**
	 * 根据用户查找地址数量
	 */
	Integer selectAddressCount(Integer uid);
	/**
	 * 查找用户的默认地址
	 * @param uid
	 * @return
	 */
	Address findDefaultByUid(Integer uid);
}
