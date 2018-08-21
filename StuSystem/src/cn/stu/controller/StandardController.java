package cn.stu.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;


import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.stu.pojo.Standard;
import cn.stu.service.StandardService;
import cn.stu.tools.PageSupport;

@Controller
public class StandardController extends BaseController {
	private Logger logger = Logger.getLogger(StandardController.class);
	@Resource
	private StandardService standardService;

	@RequestMapping(value ="/standardList.html",method=RequestMethod.GET)
	public String getStandardList(
			Model model,
			@RequestParam(value = "aparam", required = false) String aparam,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		logger.info("standardList====>queryUserName :" + aparam);
		logger.info("standardList====>pageIndex :" + pageIndex);
		List<Standard> standardList = null;
		int pageSize = 2;
		int currentPageNo = 1;
		if (aparam == null) {
			aparam = "";
		}
		if (pageIndex != null) {
			try {
				currentPageNo = Integer.parseInt(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/user/syserror.html";
			}
		}
		int totalCount = standardService.getCount(aparam);

		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		if (currentPageNo < 0) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		Integer from = (currentPageNo - 1) * pageSize;
		logger.debug("from=====" + from + "pageSize======" + pageSize);

		standardList = standardService
				.getAllStandardList(aparam, from, pageSize);
		// controller-->view
		model.addAttribute("standardList", standardList);
		
		model.addAttribute("aparam", aparam);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);

		return "standardlist";

	}

	// ��������ҳ��
	@RequestMapping(value = "/addstandard.html", method = RequestMethod.GET)
	public String addStandard(@ModelAttribute Standard standard) {
		return "addstandard";
	}
	
	

	// ���ҳ�汣����ύ����controller
	@RequestMapping(value = "/doaddstandard.html")
	public String doadd(Standard standard,
			HttpServletRequest request,
			@RequestParam(value="packagePath",required=false)MultipartFile attach) {
			String packagePath=null;
			//�ж��Ƿ�Ϊ��
			if(!attach.isEmpty()){
				//1�������ϴ�·��
				String path=request.getSession().
						getServletContext().getRealPath("files"+File.separator+"uploadfiles");
				logger.info("=====path:=======>"+path);
				//2����ȡԭ�ļ���
				String oldFileName=attach.getOriginalFilename();
				logger.info("===oldFileName===>"+oldFileName);
				//3����ȡ��׺��
				String prefix=FilenameUtils.getExtension(oldFileName);
				
				logger.info("===prefix===>"+prefix);
				
				//4����ȡ�ļ���С ���жԱ�
				int filesize=500000;
				logger.info("===size===>"+attach.getSize());
				if(attach.getSize()>filesize){
				request.setAttribute("uploadFileError", "*�ϴ��ļ���С���ó���500kB");
				return "addstandard";
				}else if(prefix.equalsIgnoreCase("jpg")
						||prefix.equalsIgnoreCase("png")
						||prefix.equalsIgnoreCase("jpeg")
						||prefix.equalsIgnoreCase("pneg")){
					//5��������
					String fileName=System.currentTimeMillis()+
							RandomUtils.nextInt(1000000)+"_person.jsp";
					logger.info("===new fileName===>"+attach.getName());
					//6������file���� pathΪĿ��·�� fileNameΪ�ļ���  ��֤���ļ�
					File targetFile=new File(path,fileName);
					if(!targetFile.exists()){
						targetFile.mkdirs();
					}
					//�����û��ϴ��ļ���
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("uploadFileError", "*�ϴ�ʧ��");
						return "addstandard";
					}
					packagePath=path+File.separator+fileName;
					
					
				}else{
					request.setAttribute("uploadFileError", "*�ϴ�ͼƬ��ʽ����ȷ");
					return "addstandard";
				}
			}
		int count=standardService.AddStandard(standard);
		if (count<1) {
			logger.debug("====>���ʧ��");
			return "addstandard";
		}else{
		return "redirect:/standardList.html";
		}
	}
	
	
	
	
	
	
	//���ӵ�ʱ���첽��ˢ����֤�������� һ����@ResponseBody ��Ȼ���صı�ʶ��ɵ�ַ��
	
	@RequestMapping(value = "/ucexist.html")
	@ResponseBody
	public String IsEqual(@RequestParam("stdNum")String stdNum) {
		HashMap<String,String> resultMap= new HashMap<String, String>();
		
		if(StringUtils.isNullOrEmpty(stdNum)){
			resultMap.put("stdNum", "exist");
		}else{
			Standard st=  standardService.IsEqual(stdNum);
			if(null!=st){//�������
				resultMap.put("stdNum", "exist");
			}else{
				resultMap.put("stdNum", "noexist");
			}
		}
			return JSONArray.toJSONString(resultMap);

	}
	
	
	
	

	// �����޸�ҳ��
	@RequestMapping(value = "/updateStandard.html", method = RequestMethod.GET)
	public String updateStandard(@RequestParam("id") String id,Model model) {
		
		Standard st=  standardService.getStandardById(Integer.parseInt(id));
		model.addAttribute("standard", st);
		return "updatestandard";
	}
	
	
		// �޸�ҳ�汣����ύ����controller
		@RequestMapping(value = "/doUpdateStandard.html")
		public String doUpdate(Standard standard) {

			if (standardService.UpdateStandard(standard) < 1) {
				logger.debug("====>�޸�ʧ��");
				return "updatestandard";
			}
			return "redirect:/standardList.html";

		}

		// ɾ������
		/*@RequestMapping(value = "/delete.html")
		@ResponseBody
		public Object alreadyDelete(@RequestParam(value = "id") String[] id, HttpSession session) {
			logger.debug("INTO StandardController.alreadyDelete()-------------------");
			boolean flag = false;
			Integer[] _id = new Integer[id.length];
			String ss="";
			int count=0;
			for (int i = 0; i < id.length; i++) {
				_id[i] = Integer.valueOf(id[i]);
				//List<Standard> standardList = standardService.findStandardList(_id[i], null, null, 0, 0);
				 count = standardService.DeleteStandard(_id[i]);
				 
				}
			if(count>0){
	             flag = true;
	             
             }
			System.out.println(flag);
			ss=JSON.toJSONString(flag);
			return "redirect:/standardList.html";
		}
		*/
		@RequestMapping(value = "/delete.html")
		@ResponseBody
		public String alreadyDelete(@RequestParam(value = "id") String[] id, HttpSession session) {
			logger.debug("INTO StandardController.alreadyDelete()-------------------");
			boolean flag = false;
			Integer[] _id = new Integer[id.length];
		// 	String ss="";
			int count=0;
			for (int i = 0; i < id.length; i++) {
				_id[i] = Integer.valueOf(id[i]);
				//List<Standard> standardList = standardService.findStandardList(_id[i], null, null, 0, 0);
				 count = standardService.DeleteStandard(_id[i]);
				 
				}
			if(count>0){
	             flag = true;
	             
             }
			System.out.println(flag);
			//return flag;
		//	ss=JSON.toJSONString(flag);
	 	return "redirect:/standardList.html?pageIndex=1";
		}
		
		
		
		
		
		
}
