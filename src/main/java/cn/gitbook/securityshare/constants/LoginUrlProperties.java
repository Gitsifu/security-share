package cn.gitbook.securityshare.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix="login.process.url")
public class LoginUrlProperties {
   private String password;
   private String sms;
   private String ukey;
}
