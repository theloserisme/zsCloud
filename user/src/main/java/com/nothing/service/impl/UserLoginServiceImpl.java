package com.nothing.service.impl;

import com.nothing.dao.UserLoginDao;
import com.nothing.service.UserLoginService;
import com.nothing.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    UserLoginDao userLoginDao;

    @Override
    public Map<String, String> verify(String iphone) {
        String host = "https://smsv2.market.alicloudapi.com";
        String path = "/sms/sendv2";
        String method = "GET";
        String appcode = "63868435735d4d6bbdb0244bbac89857";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", iphone);
        querys.put("content", "【智能云】您的验证码是568126。如非本人操作，请忽略本短信");
        //return userLoginDao.verify(username, password);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            HashMap<String, String> map = new HashMap<>();
            String s = EntityUtils.toString(response.getEntity());
            String[] split = s.replace("\"", "").replace("{", "").replace("}", "").split(",");
            for (String s1 : split) {
                String[] split1 = s1.split(":");
                map.put(split1[0], split1[1]);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String insertIphone(String iphone) {

        int i = 0;
        try {
            userLoginDao.insertIphone(iphone);
        } catch (DataAccessException e) {
            System.out.println("数据已存在，请不要重复申请");
            return "数据已存在，请不要重复申请";
        }
        System.out.println(i);
        return "消息已发送成功";
    }
}
