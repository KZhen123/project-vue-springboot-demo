package com.project.service.impl;

import com.project.mapper.UserMapper;
import com.project.model.PageQueryResultModel;
import com.project.model.dto.request.UserQuery;
import com.project.model.entity.User;
import com.project.model.vo.UserVo;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        UserQuery userQuery = new UserQuery();
        userQuery.setUsername(username);
        List<User> list = userMapper.getList(userQuery);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public int add(User user) {
        return 0;
    }

    @Override
    public PageQueryResultModel<User> selectPage(UserQuery userQuery) {
        List<User> list = userMapper.getList(userQuery);
        Long count = userMapper.getCount(userQuery);
        return new PageQueryResultModel(count, list);

    }

    @Override
    public Long selectCount(UserQuery userQuery) {
        return 0L;
    }
}
