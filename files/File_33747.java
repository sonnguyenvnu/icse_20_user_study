package com.example.jingbin.cloudreader.utils;

import com.example.jingbin.cloudreader.bean.moviechild.PersonBean;

import java.util.List;

/**
 * Created by jingbin on 2016/11/26.
 */

public class StringFormatUtil {

    /**
     * 格�?化导演�?主演�??字
     */
    public static String formatName(List<PersonBean> casts) {
        if (casts != null && casts.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < casts.size(); i++) {
                if (i < casts.size() - 1) {
                    stringBuilder.append(casts.get(i).getName()).append(" / ");
                } else {
                    stringBuilder.append(casts.get(i).getName());
                }
            }
            return stringBuilder.toString();

        } else {
            return "佚�??";
        }
    }

    /**
     * 格�?化电影类型
     */
    public static String formatGenres(List<String> genres) {
        if (genres != null && genres.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < genres.size(); i++) {
                if (i < genres.size() - 1) {
                    stringBuilder.append(genres.get(i)).append(" / ");
                } else {
                    stringBuilder.append(genres.get(i));
                }
            }
            return stringBuilder.toString();

        } else {
            return "�?知�??类型";
        }
    }

    /**
     * 获�?�剪切�?�上的链接
     */
    public static String formatUrl(String url) {
        if (url.startsWith("http")) {
            return url;
        } else if (!url.startsWith("http") && url.contains("http")) {
            // 有http且�?在头部
            url = url.substring(url.indexOf("http"), url.length());
        } else if (url.startsWith("www")) {
            // 以"www"开头
            url = "http://" + url;
        } else if (!url.startsWith("http") && (url.contains(".me") || url.contains(".com") || url.contains(".cn"))) {
            // �?以"http"开头且有�?�缀
            url = "http://www." + url;
        } else {
            url = "";
        }
        if (url.contains(" ")) {
            int i = url.indexOf(" ");
            url = url.substring(0, i);
        }
        return url;
    }
}
