package com.nt.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nt.pojo.Info;

public interface InfoMapper {

	public int selectCount(@Param("type")int type, @Param("mainWord")String mainWord);

	/*
	 * public List<Info> findByPage(HashMap<String, Object> map);
	 * 
	 * public int selectType(HashMap<String, Object> map);
	 */

	public List<Info> selectByMassage(@Param("mainWord")String mainWord, @Param("star")int star, @Param("size")int size);

	public List<Info> selectByPhone(@Param("mainWord")String mainWord,@Param("star")int star, @Param("size")int size);

	public List<Info> selectByAddress(@Param("mainWord")String mainWord, @Param("star")int star, @Param("size")int size);

	public List<Info> selectById(@Param("mainWord")String mainWord,@Param("star")int star, @Param("size")int size);

	public List<Info> selectByTitle(@Param("mainWord")String mainWord, @Param("star")int star, @Param("size")int size);

	public List<Info> selectByLinkman(@Param("mainWord")String mainWord, @Param("star")int star, @Param("size")int size);

	public void save(Info info);

}
