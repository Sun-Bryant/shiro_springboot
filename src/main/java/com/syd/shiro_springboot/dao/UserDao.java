package com.syd.shiro_springboot.dao;

import com.syd.shiro_springboot.domain.Permissions;
import com.syd.shiro_springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select *  from user where name=#{name}")
    public User findByname(String name);

    @Select("select *  from user where id=#{id}")
    public User findById(int id);

    @Select("select p.id,p.permissionName from permissions p ,`user` u, user_role ur, `role_ permission` rp Where u.name = #{name} and u.id = ur.userId  and ur.roleId = rp.roleId  and  rp.permissionId  = p.id")
    public List<Permissions> getPermissions(String name);
}
