package com.nothing.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserLoginMapper {
    @Insert("insert into user (u_account,u_password,u_username,u_alias,u_iphone) values(null,null,null,null,#{iphone}) ")
    @Options(useGeneratedKeys = true, keyProperty = "u_id",keyColumn="u_id")
    int insertIphone(@Param("iphone") String iphone);
}
