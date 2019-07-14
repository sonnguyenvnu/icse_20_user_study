package us.codecraft.webmagic.downloader;

import java.net.URI;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *支�?post 302跳转策略实现类
 *HttpClient默认跳转：httpClientBuilder.setRedirectStrategy(new LaxRedirectStrategy());
 *上述代�?在post/redirect/post这�?情况下�?会传递原有请求的数�?�信�?�。所以�?�考了下SeimiCrawler这个项目的�?定�?�策略。
 *原代�?地�?�：https://github.com/zhegexiaohuozi/SeimiCrawler/blob/master/project/src/main/java/cn/wanghaomiao/seimi/http/hc/SeimiRedirectStrategy.java
 */
public class CustomRedirectStrategy extends LaxRedirectStrategy {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        URI uri = getLocationURI(request, response, context);
        String method = request.getRequestLine().getMethod();
        if ("post".equalsIgnoreCase(method)) {
            try {
                HttpRequestWrapper httpRequestWrapper = (HttpRequestWrapper) request;
                httpRequestWrapper.setURI(uri);
                httpRequestWrapper.removeHeaders("Content-Length");
                return httpRequestWrapper;
            } catch (Exception e) {
                logger.error("强转为HttpRequestWrapper出错");
            }
            return new HttpPost(uri);
        } else {
            return new HttpGet(uri);
        }
    }
}
