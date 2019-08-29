package com.nt.service;

import java.util.HashMap;

import com.nt.pojo.Info;
import com.nt.pojo.PageBean;

public interface InfoService {
	
//	int  selectCount(HashMap<String,Object> map);
	PageBean<Info> findByPage(int currentPage, HashMap<String, Object> map);

	void saveInfo(Info info);

}
