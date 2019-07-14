package com.example.demo.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author MrBird
 */
public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Properties.class.equals(returnType.getMethod().getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Properties properties = (Properties) returnValue;

        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;

        HttpServletResponse response = servletWebRequest.getResponse();
        ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);

        // èŽ·å?–è¯·æ±‚å¤´
        HttpHeaders headers = servletServerHttpResponse.getHeaders();

        MediaType contentType = headers.getContentType();
        // èŽ·å?–ç¼–ç ?
        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
        }

        charset = charset == null ? Charset.forName("UTF-8") : charset;

        // èŽ·å?–è¯·æ±‚ä½“
        OutputStream body = servletServerHttpResponse.getBody();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(body, charset);

        properties.store(outputStreamWriter, "Serialized by PropertiesHandlerMethodReturnValueHandler#handleReturnValue");

        // å‘Šè¯‰ Spring MVC è¯·æ±‚å·²ç»?å¤„ç?†å®Œæ¯•
        mavContainer.setRequestHandled(true);
    }
}
