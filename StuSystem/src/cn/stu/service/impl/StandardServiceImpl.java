package cn.stu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.stu.dao.StandardMapper;
import cn.stu.pojo.Standard;
import cn.stu.service.StandardService;

@Service
public class StandardServiceImpl implements StandardService {
	@Autowired
	private StandardMapper standardMapper;

	public List<Standard> getAllStandardList(String aparam, Integer from,
			Integer pageSize) {
		return standardMapper.getAllStandardList(aparam, from, pageSize);
	}

	public int getCount(String aparam) {
		return standardMapper.getCount(aparam);
	}

	// 增加数据
	public int AddStandard(Standard standard) {
		return standardMapper.AddStandard(standard);
	}

	//根据id获取记录信息
		public Standard getStandardById(Integer id) {
			
		return standardMapper.getStandardById(id);
	}
	
	// 修改数据
	public int UpdateStandard(Standard standard) {
		return standardMapper.UpdateStandard(standard);
	}

	// 删除数据
	public int DeleteStandard(Integer id) {
		return standardMapper.DeleteStandard(id);
	}
	//查询重名问题
	public Standard IsEqual(String stdNum ) {
		return standardMapper.IsEqual(stdNum);
	}
}
