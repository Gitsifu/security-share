package cn.gitbook.securityshare.util;

import javax.servlet.http.HttpServletRequest;

public class AjaxUitl {
    private AjaxUitl(){}
    public static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";
}
