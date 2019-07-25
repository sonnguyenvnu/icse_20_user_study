package com.pixivic.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
final public class DefaultDownloadHttpResponse implements HttpResponse<Path> {

    private final Path defaultPath;
    private final HttpHeaders httpHeaders;

    public DefaultDownloadHttpResponse(@Value("${imageSave.default}") String path) {
        this.defaultPath = Paths.get(path);
        HttpRequest.Builder uri = HttpRequest.newBuilder()
                .uri(URI.create("https://baidu.com"))
                .header("Content-Length", "500");
        httpHeaders = uri.GET().build().headers();
    }

    @Override
    public int statusCode() {
        return 0;
    }

    @Override
    public HttpRequest request() {
        return null;
    }

    @Override
    public Optional<HttpResponse<Path>> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        return httpHeaders;
    }

    @Override
    public Path body() {
        return defaultPath;
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public URI uri() {
        return null;
    }

    @Override
    public HttpClient.Version version() {
        return null;
    }
}
