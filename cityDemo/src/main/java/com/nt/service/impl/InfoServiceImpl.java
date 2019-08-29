package com.nt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dao.InfoMapper;
import com.nt.pojo.Info;
import com.nt.pojo.PageBean;
import com.nt.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService {
	@Autowired
	private InfoMapper infoMapper;

	/*
	 * @Override public int selectCount(HashMap<String,Object> map) { return
	 * infoMapper.selectCount(type); }
	 */

	@Override
	public PageBean<Info> findByPage(int currentPage, HashMap<String, Object> map) {
		PageBean<Info> pageBean = new PageBean<Info>();


		// 每页显示的数据
		int pageSize = 3;
		pageBean.setPageSize(pageSize);

		// 给前端数据重命名int a = Integer.parseInt(str)
		int type = Integer.parseInt((String) map.get("type")) ;
		String mainWord = (String) map.get("mainWord");

		// 得到type固定的总记录数
		int totalCount = infoMapper.selectCount(type,mainWord);
		// 封装总记录数
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		// 向上取整
		Double num = Math.ceil(tc / pageSize);
		// intValue指的是失去小数位
		int totalPage=num.intValue();
		pageBean.setTotalPage(num.intValue());

		
		
		
		
        //每页显示的数据
		List<Info> list = new ArrayList<Info>();
		int star=(currentPage - 1) * pageSize;
		if(star<=0) {
			star=0;
			currentPage=1;
		}

		if(currentPage>totalPage) {
			currentPage=totalPage;
			star=(currentPage - 1) * pageSize;
			
		}
		// 封装当前页数
		pageBean.setCurrPage(currentPage);
		int size=pageBean.getPageSize();
//		map.put("star", (currentPage - 1) * pageSize);
//		map.put("size", pageBean.getPageSize());
		switch (type) {
		case 0:
			list = infoMapper.selectByMassage(mainWord, star,size);
			break;
		case 1:
			list = infoMapper.selectByPhone(mainWord,star,size);
			break;
		case 2:
			list = infoMapper.selectByAddress(mainWord,star,size);
			break;
		case 3:
			list = infoMapper.selectById(mainWord, star,size);
			break;
		case 4:
			list = infoMapper.selectByTitle(mainWord,star,size);
			break;
		case 5:
			list = infoMapper.selectByLinkman(mainWord, star,size);
			break;

		}
		pageBean.setLists(list);

		return pageBean;

	}

	@Override
	public void saveInfo(Info info) {
		infoMapper.save(info);
		
	}

}
