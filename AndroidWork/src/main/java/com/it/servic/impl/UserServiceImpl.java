package com.it.servic.impl;

import com.it.model.User;
import com.it.mapper.UserMapper;
import com.it.servic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


//    @Transactional
//    @Override
//    public Integer addUser(User user) {
//        return userMapper.insert(user);
//    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
