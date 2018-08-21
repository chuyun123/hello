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

	// 跳到增加页面
	@RequestMapping(value = "/addstandard.html", method = RequestMethod.GET)
	public String addStandard(@ModelAttribute Standard standard) {
		return "addstandard";
	}
	
	

	// 添加页面保存后提交到改controller
	@RequestMapping(value = "/doaddstandard.html")
	public String doadd(Standard standard,
			HttpServletRequest request,
			@RequestParam(value="packagePath",required=false)MultipartFile attach) {
			String packagePath=null;
			//判断是否为空
			if(!attach.isEmpty()){
				//1、定义上传路径
				String path=request.getSession().
						getServletContext().getRealPath("files"+File.separator+"uploadfiles");
				logger.info("=====path:=======>"+path);
				//2、获取原文件名
				String oldFileName=attach.getOriginalFilename();
				logger.info("===oldFileName===>"+oldFileName);
				//3、获取后缀名
				String prefix=FilenameUtils.getExtension(oldFileName);
				
				logger.info("===prefix===>"+prefix);
				
				//4、获取文件大小 进行对比
				int filesize=500000;
				logger.info("===size===>"+attach.getSize());
				if(attach.getSize()>filesize){
				request.setAttribute("uploadFileError", "*上传文件大小不得超过500kB");
				return "addstandard";
				}else if(prefix.equalsIgnoreCase("jpg")
						||prefix.equalsIgnoreCase("png")
						||prefix.equalsIgnoreCase("jpeg")
						||prefix.equalsIgnoreCase("pneg")){
					//5、重命名
					String fileName=System.currentTimeMillis()+
							RandomUtils.nextInt(1000000)+"_person.jsp";
					logger.info("===new fileName===>"+attach.getName());
					//6、创建file对象 path为目标路径 fileName为文件名  保证有文件
					File targetFile=new File(path,fileName);
					if(!targetFile.exists()){
						targetFile.mkdirs();
					}
					//接收用户上传文件流
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("uploadFileError", "*上传失败");
						return "addstandard";
					}
					packagePath=path+File.separator+fileName;
					
					
				}else{
					request.setAttribute("uploadFileError", "*上传图片格式不正确");
					return "addstandard";
				}
			}
		int count=standardService.AddStandard(standard);
		if (count<1) {
			logger.debug("====>添加失败");
			return "addstandard";
		}else{
		return "redirect:/standardList.html";
		}
	}
	
	
	
	
	
	
	//增加的时候异步无刷新验证重名问题 一定加@ResponseBody 不然返回的被识别成地址了
	
	@RequestMapping(value = "/ucexist.html")
	@ResponseBody
	public String IsEqual(@RequestParam("stdNum")String stdNum) {
		HashMap<String,String> resultMap= new HashMap<String, String>();
		
		if(StringUtils.isNullOrEmpty(stdNum)){
			resultMap.put("stdNum", "exist");
		}else{
			Standard st=  standardService.IsEqual(stdNum);
			if(null!=st){//查出来了
				resultMap.put("stdNum", "exist");
			}else{
				resultMap.put("stdNum", "noexist");
			}
		}
			return JSONArray.toJSONString(resultMap);

	}
	
	
	
	

	// 跳到修改页面
	@RequestMapping(value = "/updateStandard.html", method = RequestMethod.GET)
	public String updateStandard(@RequestParam("id") String id,Model model) {
		
		Standard st=  standardService.getStandardById(Integer.parseInt(id));
		model.addAttribute("standard", st);
		return "updatestandard";
	}
	
	
		// 修改页面保存后提交到改controller
		@RequestMapping(value = "/doUpdateStandard.html")
		public String doUpdate(Standard standard) {

			if (standardService.UpdateStandard(standard) < 1) {
				logger.debug("====>修改失败");
				return "updatestandard";
			}
			return "redirect:/standardList.html";

		}

		// 删除处理
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
