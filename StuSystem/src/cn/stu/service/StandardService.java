package cn.stu.service;

import java.util.List;

import cn.stu.pojo.Standard;

public interface StandardService {
	public List<Standard> getAllStandardList(String aparam, Integer from,
			Integer pageSize);

	public int getCount(String aparam);

	// ��������
	public int AddStandard(Standard standard);
	
	//����id��ȡ��¼��Ϣ
	public Standard getStandardById(Integer id);

	// �޸�����
	public int UpdateStandard(Standard standard);

	// ɾ������
	public int DeleteStandard( Integer id);
	
	//��ѯ��������
	public Standard IsEqual(String stdNum );
}
