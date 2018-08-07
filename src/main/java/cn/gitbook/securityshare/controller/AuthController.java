package cn.gitbook.securityshare.controller;

import cn.gitbook.securityshare.dto.JsonObject;
import cn.gitbook.securityshare.dto.PasswordLoginRequest;
import cn.gitbook.securityshare.dto.SmsLoginRequest;
import cn.gitbook.securityshare.dto.UkeyLoginRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    @PostMapping(value = "passLogin")
    @ApiOperation("模拟密码登录")
    public JsonObject<String> passLogin(@RequestBody PasswordLoginRequest request){
        return JsonObject.success("只用于生成swagger 测试接口,spring 拦截所以不会访问到此接口");
    }

    @ApiOperation("模拟短信登录接口")
    @PostMapping(value = "smsLogin")
    public JsonObject<String> smsLogin(@RequestBody SmsLoginRequest request){
        return JsonObject.success("只用于生成swagger 测试接口,spring 拦截所以不会访问到此接口");
    }

    @ApiOperation("模拟ukey登录")
    @PostMapping("ukeyLogin")
    public JsonObject<String> ukeyLogin(@RequestBody UkeyLoginRequest request){
        return JsonObject.success("只用于生成swagger 测试接口,spring 拦截所以不会访问到此接口");
    }


}
