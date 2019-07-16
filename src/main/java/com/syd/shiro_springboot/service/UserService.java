package com.syd.shiro_springboot.service;

import com.syd.shiro_springboot.dao.UserDao;
import com.syd.shiro_springboot.domain.Permissions;
import com.syd.shiro_springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByName(String name) {
        return userDao.findByname(name);
    }

    public User findById(int id){
        return userDao.findById(id);
    }

    public List<Permissions> getPermissions(String name){
        return userDao.getPermissions(name);
    }


}
