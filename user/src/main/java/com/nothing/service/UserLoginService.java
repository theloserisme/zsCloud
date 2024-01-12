package com.nothing.service;

import java.util.Map;

public interface UserLoginService {
    //手机发送信息
    Map<String, String> verify(String iphone);

    //查询数据库中是否有数据
    String insertIphone(String iphone);
}
