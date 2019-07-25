package com.sankuai.waimai.router.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.sankuai.waimai.router.components.UriTargetTools;
import com.sankuai.waimai.router.core.UriCallback;
import com.sankuai.waimai.router.core.Debugger;
import com.sankuai.waimai.router.core.UriHandler;
import com.sankuai.waimai.router.core.UriInterceptor;
import com.sankuai.waimai.router.core.UriRequest;
import com.sankuai.waimai.router.utils.CaseInsensitiveNonNullMap;
import com.sankuai.waimai.router.utils.RouterUtils;

import java.util.Map;

/**
 * 根�?�path分�?�URI给�?节点，支�?注册的�?节点包括ActivityClassName, ActivityClass, UriHandler
 *
 * Created by jzj on 2018/3/26.
 */
public class PathHandler extends UriHandler {

    /**
     * path --> UriHandler
     */
    @NonNull
    private final CaseInsensitiveNonNullMap<UriHandler> mMap = new CaseInsensitiveNonNullMap<>();
    @Nullable
    private String mPathPrefix;
    @Nullable
    private UriHandler mDefaultHandler = null;

    /**
     * 设置path�?缀
     */
    public void setPathPrefix(@Nullable String pathPrefix) {
        mPathPrefix = pathPrefix;
    }

    /**
     * 设置默认的ChildHandler。如果注册的ChildHandler�?能处�?�，则使用默认ChildHandler处�?�。
     */
    public PathHandler setDefaultChildHandler(@NonNull UriHandler handler) {
        mDefaultHandler = handler;
        return this;
    }

    /**
     * 注册一个�?节点
     *
     * @param path         path
     * @param target       支�?ActivityClassName�?ActivityClass�?UriHandler
     * @param exported     是�?��?许外部跳转
     * @param interceptors �?添加的interceptor
     */
    public void register(String path, Object target, boolean exported,
            UriInterceptor... interceptors) {
        if (!TextUtils.isEmpty(path)) {
            path = RouterUtils.appendSlash(path);
            UriHandler parse = UriTargetTools.parse(target, exported, interceptors);
            UriHandler prev = mMap.put(path, parse);
            if (prev != null) {
                Debugger.fatal("[%s] �?�?注册path='%s'的UriHandler: %s, %s", this, path, prev, parse);
            }
        }
    }

    /**
     * 注册一个�?Handler
     *
     * @param path         path
     * @param handler      支�?ActivityClassName�?ActivityClass�?UriHandler；exported默认为false
     * @param interceptors �?添加的interceptor
     */
    public void register(String path, Object handler, UriInterceptor... interceptors) {
        register(path, handler, false, interceptors);
    }

    /**
     * 注册若干个�?Handler
     */
    public void registerAll(Map<String, Object> map) {
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                register(entry.getKey(), entry.getValue());
            }
        }
    }

    private UriHandler getChild(@NonNull UriRequest request) {
        String path = request.getUri().getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        path = RouterUtils.appendSlash(path);
        if (TextUtils.isEmpty(mPathPrefix)) {
            return mMap.get(path);
        }
        if (path.startsWith(mPathPrefix)) {
            return mMap.get(path.substring(mPathPrefix.length()));
        }
        return null;
    }

    @Override
    protected boolean shouldHandle(@NonNull UriRequest request) {
        return mDefaultHandler != null || getChild(request) != null;
    }

    @Override
    protected void handleInternal(@NonNull final UriRequest request,
            @NonNull final UriCallback callback) {
        UriHandler h = getChild(request);
        if (h != null) {
            h.handle(request, new UriCallback() {
                @Override
                public void onNext() {
                    handleByDefault(request, callback);
                }

                @Override
                public void onComplete(int resultCode) {
                    callback.onComplete(resultCode);
                }
            });
        } else {
            handleByDefault(request, callback);
        }
    }

    private void handleByDefault(@NonNull UriRequest request, @NonNull UriCallback callback) {
        UriHandler defaultHandler = mDefaultHandler;
        if (defaultHandler != null) {
            defaultHandler.handle(request, callback);
        } else {
            callback.onNext();
        }
    }
}
