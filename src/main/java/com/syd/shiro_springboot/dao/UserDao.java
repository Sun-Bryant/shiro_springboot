package com.syd.shiro_springboot.dao;

import com.syd.shiro_springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select *  from user where name=#{name}")
    public User findByname(String name);

    @Select("select *  from user where id=#{id}")
    public User findById(int id);

}
