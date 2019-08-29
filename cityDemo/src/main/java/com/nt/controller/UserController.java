package com.nt.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nt.pojo.User;
import com.nt.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService uService;
	
	
	@RequestMapping("/userRegister")
	@ResponseBody
	public Map<String, Object> register(String user_name,String user_password) {
		Map<String, Object> map = new HashMap<String, Object>();
//		System.out.println(user);
		System.out.println(user_name);
		System.out.println(user_password);
		User user=new User(user_name,user_password);
		System.out.println(user);
		User u=uService.findUserByName(user_name);
		System.out.println(u);
	    if(u==null) {
	    	int f=uService.save(user);
	    	map.put("message", "注册成功");
	    	map.put("result", "1");
	    	
	    }else {
	    	map.put("message", "用户已存在");
	    	map.put("result", "0");
	    	
	    }
	    return map;
		
	}
	
	@RequestMapping("/userLogin")
	public ModelAndView login(User user,ModelAndView model) {
		User u=uService.findUser(user);
		List<User> list=new ArrayList<>();
		list=uService.findAllUser();
		if(u!=null) {
			model.addObject("user", u);
			model.addObject("users",list);
			model.setViewName("AdminTemp");	
		}else {
			model.addObject("message", "用户名或者密码错误");
			model.setViewName("login");
		}
		return model;
		
	}
	
	@RequestMapping("/searchUserById")
	public ModelAndView getPage(@RequestParam(defaultValue="1")int currentPage,Model model,User user,HttpSession session){
			System.out.println(user);
			
//		    if(user==null) {
//		    	user= (User) session.getAttribute("userForFind");
//		    }else {
//		   
//		         session.setAttribute("userForFind", user);
//		    }
//		   
		   ModelAndView mav = new ModelAndView();
		   model.addAttribute("pageUser",uService.findByPage(currentPage,user));//回显分页数据
		   
		   mav.setViewName("findUser");
	       return mav; 
		}
	
	@RequestMapping("/deleteUser")
	public void deleteUser(String user_id,HttpServletResponse response) throws Exception {
		System.out.println(user_id);
		int id=Integer.parseInt(user_id);
		uService.deleteUserById(id);
		response.setContentType("text/html; charset=UTF-8"); //转码
	    PrintWriter out = response.getWriter();
	    out.flush();
	    out.println("<script>");
	    out.println("alert('删除成功！');");
	    out.println("history.back();");
	    out.println("</script>");
	}
	


}
