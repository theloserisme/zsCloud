package com.nothing.controller;

import com.nothing.pojo.ResultVo;
import com.nothing.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class UserLoginController {
    @Autowired
    UserLoginService userLoginService;

    @GetMapping("/verify")
    public ResultVo loginVerify(@RequestParam String iphone) {
        Map<String, String> verify = userLoginService.verify(iphone);
        //String reason = verify == null ? "失败" : verify.get("reason");
        if (verify !=null ) {
            return new ResultVo(true, "消息已发送请注意查收", null);
        } else {
            return new ResultVo(false, "无法发送，处理失败", null);
        }
    }

}
