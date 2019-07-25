package com.allen.library;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.allen.library.config.OkHttpConfig;
import com.allen.library.cookie.CookieJarImpl;
import com.allen.library.cookie.store.CookieStore;
import com.allen.library.download.DownloadHelper;
import com.allen.library.factory.ApiFactory;
import com.allen.library.manage.RxHttpManager;
import com.allen.library.upload.UploadHelper;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

/**
 * Created by allen on 2017/6/22.
 *
 * @author Allen
 * 网络请求
 */

public class RxHttpUtils {

    @SuppressLint("StaticFieldLeak")
    private static RxHttpUtils instance;
    @SuppressLint("StaticFieldLeak")
    private static Application context;

    public static RxHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new RxHttpUtils();
                }
            }

        }
        return instance;
    }


    /**
     * 必须在全局Application先调用，获�?�context上下文，�?�则缓存无法使用
     *
     * @param app Application
     */
    public RxHttpUtils init(Application app) {
        context = app;
        return this;
    }

    /**
     * 获�?�全局上下文
     */
    public static Context getContext() {
        checkInitialize();
        return context;
    }

    /**
     * 检测是�?�调用�?始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.getInstance().init(this) �?始化�?");
        }
    }


    public ApiFactory config() {
        checkInitialize();
        return ApiFactory.getInstance();
    }


    /**
     * 使用全局�?�数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls) {
        return ApiFactory.getInstance().createApi(cls);
    }

    /**
     * 切�?�baseUrl
     *
     * @param baseUrlKey   域�??的key
     * @param baseUrlValue 域�??的url
     * @param cls          class
     * @param <K>          k
     * @return k
     */
    public static <K> K createApi(String baseUrlKey, String baseUrlValue, Class<K> cls) {
        return ApiFactory.getInstance().createApi(baseUrlKey, baseUrlValue, cls);
    }


    /**
     * 下载文件
     *
     * @param fileUrl 地�?�
     * @return ResponseBody
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadHelper.downloadFile(fileUrl);
    }

    /**
     * 上传�?�张图片
     *
     * @param uploadUrl 地�?�
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        return UploadHelper.uploadImage(uploadUrl, filePath);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地�?�
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImages(String uploadUrl, List<String> filePaths) {
        return UploadHelper.uploadImages(uploadUrl, filePaths);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地�?�
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    /**
     * 上传多张图片
     *
     * @param uploadUrl 地�?�
     * @param fileName  �?��?�接收文件�?的�?�数�??
     * @param paramsMap �?�数
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImagesWithParams(String uploadUrl, String fileName, Map<String, Object> paramsMap, List<String> filePaths) {
        return UploadHelper.uploadFilesWithParams(uploadUrl, fileName, paramsMap, filePaths);
    }

    /**
     * 获�?�全局的CookieJarImpl实例
     */
    private static CookieJarImpl getCookieJar() {
        return (CookieJarImpl) OkHttpConfig.getInstance().getOkHttpClient().cookieJar();
    }

    /**
     * 获�?�全局的CookieStore实例
     */
    private static CookieStore getCookieStore() {
        return getCookieJar().getCookieStore();
    }

    /**
     * 获�?�所有cookie
     */
    public static List<Cookie> getAllCookie() {
        CookieStore cookieStore = getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        return allCookie;
    }

    /**
     * 获�?��?个url所对应的全部cookie
     */
    public static List<Cookie> getCookieByUrl(String url) {
        CookieStore cookieStore = getCookieStore();
        HttpUrl httpUrl = HttpUrl.parse(url);
        List<Cookie> cookies = cookieStore.getCookie(httpUrl);
        return cookies;
    }


    /**
     * 移除全部cookie
     */
    public static void removeAllCookie() {
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeAllCookie();
    }

    /**
     * 移除�?个url下的全部cookie
     */
    public static void removeCookieByUrl(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeCookie(httpUrl);
    }

    /**
     * �?�消所有请求
     */
    public static void cancelAll() {
        RxHttpManager.get().cancelAll();
    }

    /**
     * �?�消�?个或�?些请求
     */
    public static void cancel(Object... tag) {
        RxHttpManager.get().cancel(tag);
    }
}
