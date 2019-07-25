package com.pixivic.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
final public class HeaderUtil {

    public String[] gethash() {
        SimpleDateFormat simpleDateFormat;
        String fortmat = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
        simpleDateFormat = new SimpleDateFormat(fortmat, Locale.US);
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String seed = time + "28c1fdd170a5204386cb1313c7077b34f83e4aaf4aa829ce78c231e05b0bae2c";
        byte[] digest = md5.digest(seed.getBytes());
        StringBuilder hash = new StringBuilder();
        for (int r3 = 0; r3 < digest.length; r3++)
            hash.append(String.format("%02x", digest[r3]));
        return new String[]{time, hash.toString()};
    }

    public void decorateHeader(HttpRequest.Builder builder) {
        String[] hash = gethash();
        builder.header("User-Agent", "PixivAndroidApp/5.0.93 (Android 5.1; m2)")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("App-OS", "android")
                .header("App-OS-Version", "5.1")
                .header("App-Version", "5.0.93")
                .header("Accept-Language", "zh_CN")
                .header("X-Client-Hash", hash[1])
                .header("X-Client-Time", hash[0]);
    }
}
