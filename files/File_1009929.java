package com.xiaojinzi.component.impl.interceptor;

import android.net.Uri;

import com.xiaojinzi.component.error.ignore.NavigationFailException;
import com.xiaojinzi.component.impl.RouterInterceptor;

/**
 * 这个拦截器必须在其他任何一个拦截器之�?执行
 * 从根本上�?制�?�一个界�?�在一秒钟内�?�能打开一次,这个拦截器会被框架最先执行
 * note: 这个拦截器没有连�?� {@link Uri#getScheme()} 一起判断,其实应该一起的,
 * 但是现实中应该也�?会出现一秒钟 host 和 path 都相�?�的两次路由了
 *
 * time   : 2019/01/23
 *
 * @author : xiaojinzi 30212
 */
public class OpenOnceInterceptor implements RouterInterceptor {

    private OpenOnceInterceptor() {
    }

    private static class SingletonInstance {
        private static final OpenOnceInterceptor INSTANCE = new OpenOnceInterceptor();
    }

    public static OpenOnceInterceptor getInstance() {
        return OpenOnceInterceptor.SingletonInstance.INSTANCE;
    }

    private String preHost;
    private String prePath;
    /**
     * 记录上一个界�?�跳转的时间
     */
    private long preTargetTime;

    @Override
    public void intercept(Chain chain) throws Exception {
        Uri uri = chain.request().uri;
        String currentHost = uri.getHost();
        String currentPath = uri.getPath();
        // 调试的情况下�?�能会失效,因为你断点打到这里慢慢的往下走那么�?�能时间已�?过了一秒,就失去了�?制的作用
        long currentTime = System.currentTimeMillis();
        // 如果匹�?了
        if (currentHost.equals(preHost) && currentPath.equals(prePath) && (currentTime - preTargetTime) < 1000) {
            chain.callback().onError(new NavigationFailException("target '" + uri.toString() + "' can't launch twice in a second"));
        } else {
            preHost = currentHost;
            prePath = currentPath;
            preTargetTime = currentTime;
            // 放过执行
            chain.proceed(chain.request());
        }
    }

}
