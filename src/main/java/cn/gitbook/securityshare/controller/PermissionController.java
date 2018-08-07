package cn.gitbook.securityshare.controller;


import cn.gitbook.securityshare.dto.JsonObject;
import cn.gitbook.securityshare.security.JwtUser;
import cn.gitbook.securityshare.util.SpringSecurityUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/permission")
public class PermissionController {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping(value = "getUserPermission")
    public JsonObject<List<String>> getUserPermissions(){
        JwtUser user = SpringSecurityUtil.getJwtUser();
        return JsonObject.success(user.getAuthorities().stream().map(m->((GrantedAuthority) m).getAuthority()).collect(Collectors.toList()));
    }
}
