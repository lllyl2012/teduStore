package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
	/**
	 * 添加地址
	 * @param address
	 */
	void addAddress(Address address);
	/**
	 * 删除地址
	 */
	void deleteAddress(@Param("id") Integer id,@Param("uid") Integer uid);
	/**
	 * 根据用户查找地址
	 */
	List<Address> findByUid(@Param("uid") Integer uid);
	/**
	 * 设定全部不默认
	 */
	void setAllNotDefault();
	/**
	 * 设定全部默认
	 * @param id
	 */
	void setDefault(@Param("id") Integer id);
	/**
	 * 根据id查找地址
	 */
	Address findById(@Param("id") Integer id);
	/**
	 * 查找当前用户的地址数量
	 */
	Integer selectAddressCount(@Param("uid") Integer uid);
	/**
	 * 根据用户uid查找默认地址
	 * @param uid
	 * @return
	 */
	Address findDefaultByUid(Integer uid);
}
