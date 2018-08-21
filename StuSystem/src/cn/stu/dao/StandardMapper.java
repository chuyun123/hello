package cn.stu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.stu.pojo.Standard;



public interface StandardMapper {
	
	//1���鿴���е���Ϣ
		public List<Standard> getAllStandardList(@Param("aparam")String aparam,
											@Param("from")Integer from,
											@Param("pageSize")Integer pageSize);
		//����������ȡ�ܼ�¼��
		public int getCount(@Param("aparam")String aparam);
		
		//��������
		public int AddStandard(Standard standard);
		
		//����id��ȡ��¼��Ϣ
		public Standard getStandardById(@Param("id")Integer id);
		
		//�޸�����
		public int UpdateStandard(Standard standard);
		//ɾ������
		public int DeleteStandard(@Param("id")Integer id);
			
		//��ѯ��������
		public Standard IsEqual(@Param("stdNum")String stdNum );
		
		
		
		

}
