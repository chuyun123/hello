package cn.stu.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
/**
 * 鏃堕棿鏁版嵁杞崲 1銆佸垱寤築aseController.java 骞跺湪鏂规硶涓婃爣娉ˊInitBinder
 * 			    2銆佽鍏朵粬controller缁ф壙姝ょ被 
 * @author Administrator
 *
 *   registerCustomEditor 娉ㄥ唽涓�釜鑷畾涔夌紪杈戝櫒   
 *		CustomDateEditor  鑷畾涔夌紪杈戝櫒
 */
public class BaseController {
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		System.out.println("initBider========>");
	dataBinder.registerCustomEditor(Date.class, 
			new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		
	}

}
