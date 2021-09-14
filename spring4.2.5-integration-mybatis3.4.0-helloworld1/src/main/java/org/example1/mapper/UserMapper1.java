package org.example1.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper1 {
	
	
	String loadById(String id);
			

}
