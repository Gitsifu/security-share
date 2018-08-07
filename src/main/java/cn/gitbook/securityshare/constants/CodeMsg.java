package cn.gitbook.securityshare.constants;

/**
 * 本code码只用于临时编码，更好方式是业务系统根据模块进行编码，约定好业务前缀，做好业务的区分，这样根据code码就知道哪个模块的异常，
 * 尽量不要随意定义
 */
public enum CodeMsg {
    sucess(1L,"操作成功"),
    error(0L,"操作失败"),
    sysError(3L,"系统异常"),
    forceLoginOut(1000001L,"您的账号在其他设备登录"),
    hasUserOnline(1000002L,"当前已有用户在登录"),
    tokenInvalid(1000003L,"登录已经过期，请重新登录"),
    tokenIllegal(1000004L,"无效令牌"),
    methodNotSupport(1000005L,"请求的方法不支持"),
    passwordIncorrect(1000006L,"用户名或者密码错误"),
    accessDenied(1000007L,"没有操作该功能的权限"),
    ukeyNotSupported(1000008L,"ukey登录需要集成银行环境所以暂不支持"),
    paramLack(1000009L,"请求参数有误");
    private Long code;
    private String msg;
    CodeMsg(Long code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    public CodeMsg of(Long code){
        for(CodeMsg em : CodeMsg.values()){
            if(em.getCode().longValue() == code){
                return em;
            }
        }
        return null;
    }
}
