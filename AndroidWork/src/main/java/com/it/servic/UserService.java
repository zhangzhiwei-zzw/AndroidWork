package com.it.servic;

import com.it.model.User;

import java.util.List;

public interface UserService {

//    Integer addUser(User user);

    List<User> selectAllUser();

    User getUserById(String id);

    Integer insertUser(User user);

}
