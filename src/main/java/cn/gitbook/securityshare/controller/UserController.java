package cn.gitbook.securityshare.controller;

import cn.gitbook.securityshare.dto.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "usesr")
public class UserController {


    public JsonObject getUserInfo(){
        //JsonObject.success()
        return null;
    }
}
