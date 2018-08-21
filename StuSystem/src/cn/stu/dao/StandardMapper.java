package cn.stu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.stu.pojo.Standard;



public interface StandardMapper {
	
	//1、查看所有的信息
		public List<Standard> getAllStandardList(@Param("aparam")String aparam,
											@Param("from")Integer from,
											@Param("pageSize")Integer pageSize);
		//根据条件获取总记录数
		public int getCount(@Param("aparam")String aparam);
		
		//增加数据
		public int AddStandard(Standard standard);
		
		//根据id获取记录信息
		public Standard getStandardById(@Param("id")Integer id);
		
		//修改数据
		public int UpdateStandard(Standard standard);
		//删除数据
		public int DeleteStandard(@Param("id")Integer id);
			
		//查询重名问题
		public Standard IsEqual(@Param("stdNum")String stdNum );
		
		
		
		

}
