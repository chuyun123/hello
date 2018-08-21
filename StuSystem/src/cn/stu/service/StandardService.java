package cn.stu.service;

import java.util.List;

import cn.stu.pojo.Standard;

public interface StandardService {
	public List<Standard> getAllStandardList(String aparam, Integer from,
			Integer pageSize);

	public int getCount(String aparam);

	// 增加数据
	public int AddStandard(Standard standard);
	
	//根据id获取记录信息
	public Standard getStandardById(Integer id);

	// 修改数据
	public int UpdateStandard(Standard standard);

	// 删除数据
	public int DeleteStandard( Integer id);
	
	//查询重名问题
	public Standard IsEqual(String stdNum );
}
