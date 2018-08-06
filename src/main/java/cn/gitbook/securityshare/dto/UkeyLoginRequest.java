package cn.gitbook.securityshare.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("ukey 登录model")
@Data
public class UkeyLoginRequest {
    @ApiModelProperty(value = "真实姓名",required = true)
    private String realName;
    @ApiModelProperty(value = "加密的密文",required = true)
    private String encodePass;
}
