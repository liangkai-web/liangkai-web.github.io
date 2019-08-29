package com.nt.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nt.pojo.Info;
import com.nt.service.InfoService;
import com.nt.utils.FcantUtils;

@Controller
@RequestMapping("/info")
public class InfoController {
	@Autowired
	private InfoService infoService;
	
	
    //	String type,String mainWord表示前台参数
	@RequestMapping("/searchShow")
	public ModelAndView getPage(@RequestParam(defaultValue="1")int currentPage,Model model,String
			type,String mainWord,HttpSession session){
		    HashMap<String,Object> map=new HashMap<>();
		    if(type==""&&mainWord=="") {
		    	 map=(HashMap<String, Object>) session.getAttribute("MAP");
		    }else {
		    	map.put("type", type);
				   map.put("mainWord", mainWord);
				   session.setAttribute("MAP", map);
		    }
//		   if(session.getAttribute("MAP")==null) {
//			   map.put("type", type);
//			   map.put("mainWord", mainWord);
//			   session.setAttribute("MAP", map);
//		   }else {
//			  
//		   }
		   
		   ModelAndView mav = new ModelAndView();
		   model.addAttribute("pagemsg",infoService.findByPage(currentPage,map));//回显分页数据
		   mav.setViewName("find");
	       return mav; 
		}
	@RequestMapping("/infoAdd")
	public ModelAndView infoAdd(Info info,ModelAndView model) throws ParseException {
		Date date= new Date();
		info.setInfo_data(FcantUtils.formatDate(date));
		infoService.saveInfo(info);
		model.setViewName("indextemp");
		return model;
	}
}
