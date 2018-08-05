package cn.gitbook.securityshare.constants;

public enum CodeMsg {
    sucess(1L,"操作成功"),
    error(0L,"操作失败"),
    accessDenied(10000L,"没有操作该功能的权限");
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
