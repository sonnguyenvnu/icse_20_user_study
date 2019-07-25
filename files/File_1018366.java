package com.pixivic.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
final public class OauthUtil {
    private final HttpClient httpClient;
    private final HeaderUtil headerUtil;
    private final HttpUtil httpUtil;
    @Getter
    private String access_token;
    @Value("${pixiv.client_id}")
    private String client_id;
    @Value("${pixiv.client_secret}")
    private String client_secret;
    @Value("${pixiv.username}")
    private String username;
    @Value("${pixiv.password}")
    private String password;
    @Value("${pixiv.device_token}")
    private String device_token;
    @Value("${pixiv.refresh_token}")
    private String refresh_token;
    private String param;

    @PostConstruct
    private void init() throws IOException, InterruptedException, NoSuchAlgorithmException {
        Map<String, String> paramMap = new HashMap<>(
                Map.of("client_id", client_id, "client_secret", client_secret, "grant_type", "password"
                        , "username", username, "password", password, "device_token", device_token
                        , "get_secure_url", "true"));
        setAccess_token(refreshToken(httpUtil.getPostEntity(paramMap)));
        paramMap.replace("grant_type", "refresh_token");
        paramMap.put("refresh_token", refresh_token);
        param = httpUtil.getPostEntity(paramMap);
    }

    private String refreshToken(String param) throws IOException, InterruptedException, NoSuchAlgorithmException {
        HttpRequest.Builder uri = HttpRequest.newBuilder()
                .uri(URI.create("https://oauth.api.pixivic.com/auth/token"));
        headerUtil.decorateHeader(uri);
        HttpRequest oauth = uri.POST(HttpRequest.BodyPublishers.ofString(param))
                .build();
        return httpClient.send(oauth, HttpResponse.BodyHandlers.ofString()).body();
    }

    private void setAccess_token(String response) {
        int index = response.indexOf("\"access_token\":\"");
        access_token = response.substring(index + 16, index + 59);
    }

    @Scheduled(cron = "0 0 */1 * * ?")
    public void RefreshAccess_token() throws InterruptedException, NoSuchAlgorithmException, IOException {
        setAccess_token(refreshToken(param));
    }
}
