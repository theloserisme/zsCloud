package com.nothing.dao.impl;

import com.nothing.dao.UserLoginDao;
import com.nothing.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginDaoImpl implements UserLoginDao {
    @Autowired
    UserLoginMapper loginMapper;

    @Override
    public int insertIphone(String iphone) {
        return loginMapper.insertIphone(iphone);
    }
}
