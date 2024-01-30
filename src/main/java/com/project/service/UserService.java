package com.project.service;

import com.project.model.PageQueryResultModel;
import com.project.model.dto.request.UserQuery;
import com.project.model.entity.User;
import com.project.model.vo.UserVo;

import java.util.List;

public interface UserService {
    User getUserByName(String username);

    int add(User user);

    PageQueryResultModel<User> selectPage(UserQuery userQuery);

    Long selectCount(UserQuery userQuery);
}
