package org.nutz.mvc.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.View;

/**
 * 返回特定的�?应�?
 * <p/>
 * <b>注�?,400或以上,会调用resp.sendError,而�?�resp.setStatus.这样�?�的原因是
 * errorPage的�?置,�?�有resp.sendError会触�?�,且�?大多数情况下,�?�会�?置400或以上</b>
 * 
 * @author MingMing
 * 
 */
public class HttpStatusView implements View {

    public static final View HTTP_404 = new HttpStatusView(404);
    // public static final View HTTP_400 = new HttpStatusView(400);
    public static final View HTTP_500 = new HttpStatusView(500);
    public static final View HTTP_502 = new HttpStatusView(502);

    public static HttpStatusException makeThrow(int status, String body) {
        return new HttpStatusException(status, body);
    }

    /**
     * 这个异常用于，在�?个入�?�函数,如果你声明了 `@Fail("http:500")` 但是你真正的返回值想根�?��?行时决定。 <br>
     * 那么，你就直接抛这个异常好了
     * 
     * @author zozoh(zozohtnt@gmail.com)
     */
    public static class HttpStatusException extends RuntimeException {

        private static final long serialVersionUID = 4035188583429445028L;

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public HttpStatusException(int status) {
            this.status = status;
        }

        public HttpStatusException(int status, String fmt, Object... args) {
            super(String.format(fmt, args));
            this.status = status;
        }

    }

    private HttpEnhanceResponse info;

    public HttpStatusView(HttpEnhanceResponse info) {
        this.info = info;
    }

    public HttpStatusView(int statusCode) {
        info = new HttpEnhanceResponse();
        info.updateCode(statusCode, null);
    }

    public HttpStatusView(Map<?, ?> map) {
        this(200);
        info.update(map);
    }

    public HttpStatusView setBody(String body) {
        info.updateBody(body);
        return this;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp, Object obj) {
        HttpEnhanceResponse info = this.info.clone();

        if (null != obj) {
            // 指明了动�?的 code
            if (obj instanceof HttpStatusException) {
                HttpStatusException hse = ((HttpStatusException) obj);
                info.updateCode(hse.getStatus(), null);
                info.updateBody(hse.getMessage());
            }
            // 指明了 Header
            else if (obj instanceof Map<?, ?>) {
                info.update((Map<?, ?>) obj);
            }
            // 字符串 ...
            else if (obj instanceof CharSequence) {
                info.updateBy(obj.toString());
            }
        }

        // 执行渲染
        info.render(resp);

        // if (code >= 400){
        // try {
        // resp.sendError(code);
        // }
        // catch (IOException e) {
        // throw Lang.wrapThrow(e);
        // }
        // }
        // else
        // resp.setStatus(code);

    }

}
