package com.example.demo.mapper;

import com.example.demo.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Usermapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_identify) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_identify})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findbytoken(@Param("token") String token);


}
