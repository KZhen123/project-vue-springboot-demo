package com.project.mapper;

import com.project.model.dto.request.UserQuery;
import com.project.model.entity.User;
import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> getList(UserQuery userQuery);

    Long getCount(UserQuery userQuery);
}