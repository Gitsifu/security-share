package cn.gitbook.securityshare.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

//短信发送记录实体类
@Data
@Builder
public class SmsRecord {
    private Long id;
    private Date sendTime;
    private Date expireTime;
    private String phone;
    private String code;
}
