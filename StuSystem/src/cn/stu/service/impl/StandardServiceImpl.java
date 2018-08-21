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

	// ��������
	public int AddStandard(Standard standard) {
		return standardMapper.AddStandard(standard);
	}

	//����id��ȡ��¼��Ϣ
		public Standard getStandardById(Integer id) {
			
		return standardMapper.getStandardById(id);
	}
	
	// �޸�����
	public int UpdateStandard(Standard standard) {
		return standardMapper.UpdateStandard(standard);
	}

	// ɾ������
	public int DeleteStandard(Integer id) {
		return standardMapper.DeleteStandard(id);
	}
	//��ѯ��������
	public Standard IsEqual(String stdNum ) {
		return standardMapper.IsEqual(stdNum);
	}
}
