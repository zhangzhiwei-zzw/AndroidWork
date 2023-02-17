package com.it.mapper;


import com.it.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAllUser();

    User getUserById(String  id);

    Integer insertUser(User user);
}