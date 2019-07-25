package com.xiaojinzi.component.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnyThread;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;

import com.xiaojinzi.component.Component;
import com.xiaojinzi.component.ComponentUtil;
import com.xiaojinzi.component.RouterRxFragment;
import com.xiaojinzi.component.bean.ActivityResult;
import com.xiaojinzi.component.error.ignore.ActivityResultException;
import com.xiaojinzi.component.error.ignore.InterceptorNotFoundException;
import com.xiaojinzi.component.error.ignore.NavigationFailException;
import com.xiaojinzi.component.impl.interceptor.InterceptorCenter;
import com.xiaojinzi.component.impl.interceptor.OpenOnceInterceptor;
import com.xiaojinzi.component.support.Action;
import com.xiaojinzi.component.support.CallbackAdapter;
import com.xiaojinzi.component.support.Consumer;
import com.xiaojinzi.component.support.NavigationDisposable;
import com.xiaojinzi.component.support.RouterInterceptorCache;
import com.xiaojinzi.component.support.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 这个类一部分功能应该是 {@link Router} 的构建者对象的功能,但是这里�?�更多的为导航的功能
 * 写了很多代�?,所以�??字就�?�?� Builder 了
 */
public class Navigator extends RouterRequest.Builder implements Call {

    /**
     * requestCode 如果等于这个值,就表示是�?机生�?的
     * 从 1-256 中�?机生�?一个,如果生�?的正好是目�?正在用的,会�?新生�?一个
     */
    static final Integer RANDOM_REQUSET_CODE = Integer.MIN_VALUE;

    /**
     * 自定义的拦截器列表,为了�?�?顺�?�?用一个集�?�的
     * 1. RouterInterceptor 类型
     * 2. Class<RouterInterceptor> 类型
     * 3. String 类型
     * 其他类型会 debug 的时候报错
     */
    @Nullable
    private List<Object> customInterceptors;

    /**
     * 标记这个 builder 是�?�已�?被使用了,使用过了就�?能使用了
     */
    protected boolean isFinish = false;

    public Navigator() {
    }

    public Navigator(@NonNull Context context) {
        Utils.checkNullPointer(context, "context");
        context(context);
    }

    public Navigator(@NonNull Fragment fragment) {
        Utils.checkNullPointer(fragment, "fragment");
        fragment(fragment);
    }

    /**
     * 懒加载自定义拦截器列表
     *
     * @param size
     */
    private void lazyInitCustomInterceptors(int size) {
        if (customInterceptors == null) {
            customInterceptors = new ArrayList<>(size > 3 ? size : 3);
        }
    }

    public Navigator interceptors(RouterInterceptor... interceptorArr) {
        Utils.checkNullPointer(interceptorArr, "interceptorArr");
        if (interceptorArr != null) {
            lazyInitCustomInterceptors(interceptorArr.length);
            customInterceptors.addAll(Arrays.asList(interceptorArr));
        }
        return this;
    }

    public Navigator interceptors(Class<? extends RouterInterceptor>... interceptorClassArr) {
        Utils.checkNullPointer(interceptorClassArr, "interceptorClassArr");
        if (interceptorClassArr != null) {
            lazyInitCustomInterceptors(interceptorClassArr.length);
            customInterceptors.addAll(Arrays.asList(interceptorClassArr));
        }
        return this;
    }

    public Navigator interceptorNames(String... interceptorNameArr) {
        Utils.checkNullPointer(interceptorNameArr, "interceptorNameArr");
        if (interceptorNameArr != null) {
            lazyInitCustomInterceptors(interceptorNameArr.length);
            customInterceptors.addAll(Arrays.asList(interceptorNameArr));
        }
        return this;
    }

    @Override
    public Navigator intentConsumer(@Nullable Consumer<Intent> intentConsumer) {
        return (Navigator) super.intentConsumer(intentConsumer);
    }

    @Override
    public Navigator addIntentFlags(@Nullable Integer... flags) {
        return (Navigator) super.addIntentFlags(flags);
    }

    @Override
    public Navigator addIntentCategories(@Nullable String... categories) {
        return (Navigator) super.addIntentCategories(categories);
    }

    @Override
    public Navigator beforJumpAction(@Nullable Action action) {
        return (Navigator) super.beforJumpAction(action);
    }

    @Override
    public Navigator afterJumpAction(@Nullable Action action) {
        return (Navigator) super.afterJumpAction(action);
    }

    @Override
    public Navigator requestCode(@Nullable Integer requestCode) {
        return (Navigator) super.requestCode(requestCode);
    }

    @Override
    public Navigator options(@Nullable Bundle options) {
        return (Navigator) super.options(options);
    }

    /**
     * requestCode 会�?机的生�?
     *
     * @return
     */
    public Navigator requestCodeRandom() {
        return requestCode(RANDOM_REQUSET_CODE);
    }

    @Override
    public Navigator url(@NonNull String url) {
        return (Navigator) super.url(url);
    }

    @Override
    public Navigator scheme(@NonNull String scheme) {
        return (Navigator) super.scheme(scheme);
    }

    @Override
    public Navigator hostAndPath(@NonNull String hostAndPath) {
        return (Navigator) super.hostAndPath(hostAndPath);
    }

    @Override
    public Navigator host(@NonNull String host) {
        return (Navigator) super.host(host);
    }

    @Override
    public Navigator path(@Nullable String path) {
        return (Navigator) super.path(path);
    }

    @Override
    public Navigator putBundle(@NonNull String key, @Nullable Bundle bundle) {
        return (Navigator) super.putBundle(key, bundle);
    }

    @Override
    public Navigator putAll(@NonNull Bundle bundle) {
        return (Navigator) super.putAll(bundle);
    }

    @Override
    public Navigator putCharSequence(@NonNull String key, @Nullable CharSequence value) {
        return (Navigator) super.putCharSequence(key, value);
    }

    @Override
    public Navigator putCharSequenceArray(@NonNull String key, @Nullable CharSequence[] value) {
        return (Navigator) super.putCharSequenceArray(key, value);
    }

    @Override
    public Navigator putCharSequenceArrayList(@NonNull String key, @Nullable ArrayList<CharSequence> value) {
        return (Navigator) super.putCharSequenceArrayList(key, value);
    }

    @Override
    public Navigator putByte(@NonNull String key, @Nullable byte value) {
        return (Navigator) super.putByte(key, value);
    }

    @Override
    public Navigator putByteArray(@NonNull String key, @Nullable byte[] value) {
        return (Navigator) super.putByteArray(key, value);
    }

    @Override
    public Navigator putChar(@NonNull String key, @Nullable char value) {
        return (Navigator) super.putChar(key, value);
    }

    @Override
    public Navigator putCharArray(@NonNull String key, @Nullable char[] value) {
        return (Navigator) super.putCharArray(key, value);
    }

    @Override
    public Navigator putBoolean(@NonNull String key, @Nullable boolean value) {
        return (Navigator) super.putBoolean(key, value);
    }

    @Override
    public Navigator putBooleanArray(@NonNull String key, @Nullable boolean[] value) {
        return (Navigator) super.putBooleanArray(key, value);
    }

    @Override
    public Navigator putString(@NonNull String key, @Nullable String value) {
        return (Navigator) super.putString(key, value);
    }

    @Override
    public Navigator putStringArray(@NonNull String key, @Nullable String[] value) {
        return (Navigator) super.putStringArray(key, value);
    }

    @Override
    public Navigator putStringArrayList(@NonNull String key, @Nullable ArrayList<String> value) {
        return (Navigator) super.putStringArrayList(key, value);
    }

    @Override
    public Navigator putShort(@NonNull String key, @Nullable short value) {
        return (Navigator) super.putShort(key, value);
    }

    @Override
    public Navigator putShortArray(@NonNull String key, @Nullable short[] value) {
        return (Navigator) super.putShortArray(key, value);
    }

    @Override
    public Navigator putInt(@NonNull String key, @Nullable int value) {
        return (Navigator) super.putInt(key, value);
    }

    @Override
    public Navigator putIntArray(@NonNull String key, @Nullable int[] value) {
        return (Navigator) super.putIntArray(key, value);
    }

    @Override
    public Navigator putIntegerArrayList(@NonNull String key, @Nullable ArrayList<Integer> value) {
        return (Navigator) super.putIntegerArrayList(key, value);
    }

    @Override
    public Navigator putLong(@NonNull String key, @Nullable long value) {
        return (Navigator) super.putLong(key, value);
    }

    @Override
    public Navigator putLongArray(@NonNull String key, @Nullable long[] value) {
        return (Navigator) super.putLongArray(key, value);
    }

    @Override
    public Navigator putFloat(@NonNull String key, @Nullable float value) {
        return (Navigator) super.putFloat(key, value);
    }

    @Override
    public Navigator putFloatArray(@NonNull String key, @Nullable float[] value) {
        return (Navigator) super.putFloatArray(key, value);
    }

    @Override
    public Navigator putDouble(@NonNull String key, @Nullable double value) {
        return (Navigator) super.putDouble(key, value);
    }

    @Override
    public Navigator putDoubleArray(@NonNull String key, @Nullable double[] value) {
        return (Navigator) super.putDoubleArray(key, value);
    }

    @Override
    public Navigator putParcelable(@NonNull String key, @Nullable Parcelable value) {
        return (Navigator) super.putParcelable(key, value);
    }

    @Override
    public Navigator putParcelableArray(@NonNull String key, @Nullable Parcelable[] value) {
        return (Navigator) super.putParcelableArray(key, value);
    }

    @Override
    public Navigator putParcelableArrayList(@NonNull String key, @Nullable ArrayList<? extends Parcelable> value) {
        return (Navigator) super.putParcelableArrayList(key, value);
    }

    @Override
    public Navigator putSparseParcelableArray(@NonNull String key, @Nullable SparseArray<? extends Parcelable> value) {
        return (Navigator) super.putSparseParcelableArray(key, value);
    }

    @Override
    public Navigator putSerializable(@NonNull String key, @Nullable Serializable value) {
        return (Navigator) super.putSerializable(key, value);
    }

    @Override
    public Navigator query(@NonNull String queryName, @Nullable String queryValue) {
        return (Navigator) super.query(queryName, queryValue);
    }

    @Override
    public Navigator query(@NonNull String queryName, boolean queryValue) {
        return (Navigator) super.query(queryName, queryValue);
    }

    @Override
    public Navigator query(@NonNull String queryName, byte queryValue) {
        return (Navigator) super.query(queryName, queryValue);
    }

    @Override
    public Navigator query(@NonNull String queryName, int queryValue) {
        return (Navigator) super.query(queryName, queryValue);
    }

    @Override
    public Navigator query(@NonNull String queryName, float queryValue) {
        return (Navigator) super.query(queryName, queryValue);
    }

    @Override
    public Navigator query(@NonNull String queryName, long queryValue) {
        return (Navigator) super.query(queryName, queryValue);
    }

    @Override
    public Navigator query(@NonNull String queryName, double queryValue) {
        return (Navigator) super.query(queryName, queryValue);
    }

    @Override
    public RouterRequest build() {
        return Help.randomlyGenerateRequestCode(super.build());
    }

    private void useDefaultApplication(){
        // 如果 Context 和 Fragment 都是空的,使用默认的 Application
        if (context == null && fragment == null) {
            context = Component.getApplication();
            addIntentFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
    }

    /**
     * 路由�?的检查
     *
     * @throws Exception
     */
    private void onCheck() {
        // 一个 Builder �?能被使用多次
        if (isFinish) {
            throw new NavigationFailException("Builder can't be used multiple times");
        }
        // 检查上下文和fragment
        if (context == null && fragment == null) {
            throw new NullPointerException("the parameter 'context' or 'fragment' both are null");
        }
    }

    /**
     * 检查�?�数,这个方法和 {@link #onCheck()} 很多项目都一样的,但是没办法
     * 这里的检查是需�?�??�?检查的
     * 父类的检查是调用 {@link #navigate(Callback)}方法的时候调用 {@link #onCheck()} 检查的
     * 这个类是调用 {@link #navigate(Callback)} 方法之�?检查的,而且检查的项目虽然基本一样,但是有所差别
     *
     * @throws RuntimeException
     */
    private void onCheckForResult() throws Exception {
        if (context == null && fragment == null) {
            throw new NavigationFailException(new NullPointerException("Context or Fragment is necessary for router if you want get ActivityResult"));
        }
        // 如果是使用 Context 的,那么就必须是 FragmentActivity,需�?�?作 Fragment
        // 这里的 context != null 判断�?�件�?能去掉,�?然使用 Fragment 跳转的就过�?去了
        if (context != null && !(Utils.getActivityFromContext(context) instanceof FragmentActivity)) {
            throw new NavigationFailException(new IllegalArgumentException("Context must be FragmentActivity"));
        }
        if (requestCode == null) {
            throw new NavigationFailException(new NullPointerException("requestCode must not be null for router"));
        }
    }

    /**
     * 为了拿到 {@link ActivityResult#resultCode}
     *
     * @param callback 回调方法
     * @return
     */
    @NonNull
    public NavigationDisposable navigateForResultCode(@NonNull final BiCallback<Integer> callback) {
        return navigateForResult(new BiCallback.Map<ActivityResult, Integer>(callback) {
            @NonNull
            @Override
            public Integer apply(@NonNull ActivityResult activityResult) throws Exception {
                return activityResult.resultCode;
            }
        });
    }

    /**
     * 为了拿到 {@link ActivityResult#resultCode}
     *
     * @param callback 回调方法
     * @return
     */
    @NonNull
    public NavigationDisposable navigateForResultCodeMatch(@NonNull final Callback callback,
                                                           final int expectedResultCode) {
        return navigateForResult(new BiCallback<ActivityResult>() {
            @Override
            public void onSuccess(@NonNull RouterResult result, @NonNull ActivityResult activityResult) {
                if (expectedResultCode == activityResult.resultCode) {
                    callback.onSuccess(result);
                } else {
                    callback.onError(new RouterErrorResult(result.getOriginalRequest(), new ActivityResultException("the resultCode is not matching " + expectedResultCode)));
                }
            }

            @Override
            public void onError(@NonNull RouterErrorResult errorResult) {
                callback.onError(errorResult);
            }

            @Override
            public void onCancel(@NonNull RouterRequest originalRequest) {
                callback.onCancel(originalRequest);
            }
        });
    }

    /**
     * 为了拿到 {@link Intent}
     *
     * @param callback 回调方法
     * @return
     */
    @NonNull
    public NavigationDisposable navigateForIntentAndResultCodeMatch(@NonNull final BiCallback<Intent> callback,
                                                                    final int expectedResultCode) {
        return navigateForResult(new BiCallback.Map<ActivityResult, Intent>(callback) {
            @NonNull
            @Override
            public Intent apply(@NonNull ActivityResult activityResult) throws Exception {
                return activityResult.intentWithResultCodeCheckAndGet(expectedResultCode);
            }
        });
    }

    /**
     * 为了拿到 {@link Intent}
     *
     * @param callback 回调方法
     * @return
     */
    @NonNull
    public NavigationDisposable navigateForIntent(@NonNull final BiCallback<Intent> callback) {
        return navigateForResult(new BiCallback.Map<ActivityResult, Intent>(callback) {
            @NonNull
            @Override
            public Intent apply(@NonNull ActivityResult activityResult) throws Exception {
                return activityResult.intentCheckAndGet();
            }
        });
    }


    /**
     * 为了拿 {@link ActivityResult}
     *
     * @param callback 这里是为了拿返回的东西是�?�?�以为空的
     * @return
     */
    @NonNull
    @AnyThread
    public NavigationDisposable navigateForResult(@NonNull final BiCallback<ActivityResult> callback) {
        return realNavigateForResult(callback);
    }

    @NonNull
    @AnyThread
    private NavigationDisposable realNavigateForResult(@NonNull final BiCallback<ActivityResult> callback) {

        final NavigationDisposable.ProxyNavigationDisposableImpl proxyDisposable =
                new NavigationDisposable.ProxyNavigationDisposableImpl();

        // 主线程执行
        Utils.postActionToMainThread(new Runnable() {
            @Override
            public void run() {
                // 这里这个情况属于没开始就被�?�消了
                if (proxyDisposable.isCanceled()) {
                    RouterUtil.cancelCallback(null, callback);
                    return;
                }
                final NavigationDisposable realDisposable = doNavigateForResult(callback);
                proxyDisposable.setProxy(realDisposable);

            }
        });

        return proxyDisposable;

    }

    /**
     * 必须在主线程中调用,就这里�?�能会出现一�?特殊的情况：
     * 用户收到的回调�?�能是 error,但是全局的监�?��?�能是 cancel,其实这个问题也能解决,
     * 就是路由调用之�?�??�?通过方法 {@link Navigator#build()} �??�?构建一个 {@link RouterRequest} 出�?�判断
     * 但是没有那个必�?去�?�这件事情了,等到有必�?的时候�?说,基本�?会出现并且出现了也�?是什么问题
     *
     * @param biCallback
     * @return
     */
    @NonNull
    @MainThread
    private NavigationDisposable doNavigateForResult(@NonNull final BiCallback<ActivityResult> biCallback) {
        // 直接 gg
        Utils.checkNullPointer(biCallback, "callback");
        // �?�一个包裹实现至多�?�能调用一次内部的其中一个方法
        final BiCallback<ActivityResult> callback = new BiCallbackWrap<>(biCallback);
        NavigationDisposable finalNavigationDisposable = null;
        try {
            // 为了拿数�?��?�的检查
            onCheckForResult();
            // 声明fragment
            FragmentManager fm = null;
            if (context == null) {
                fm = fragment.getChildFragmentManager();
            } else {
                fm = ((FragmentActivity) Utils.getActivityFromContext(context)).getSupportFragmentManager();
            }
            // 寻找是�?�添加过 Fragment
            RouterRxFragment findRxFragment = (RouterRxFragment) fm.findFragmentByTag(ComponentUtil.FRAGMENT_TAG);
            if (findRxFragment == null) {
                findRxFragment = new RouterRxFragment();
                fm.beginTransaction()
                        .add(findRxFragment, ComponentUtil.FRAGMENT_TAG)
                        .commitAllowingStateLoss();
            }
            final RouterRxFragment rxFragment = findRxFragment;
            // 导航方法执行完毕之�?�,内部的数�?�就会清空,所以之�?必须缓存
            // 导航拿到 NavigationDisposable 对象
            // �?�能是一个 空实现
            finalNavigationDisposable = navigate(new CallbackAdapter() {
                @Override
                @MainThread
                public void onSuccess(@NonNull final RouterResult routerResult) {
                    super.onSuccess(routerResult);
                    // 设置ActivityResult回调的�?�射器,回调中一个路由拿数�?�的�?程算是完毕了
                    rxFragment.setActivityResultConsumer(routerResult.getOriginalRequest(), new com.xiaojinzi.component.support.Consumer<ActivityResult>() {
                        @Override
                        public void accept(@NonNull ActivityResult result) throws Exception {
                            Help.removeRequestCode(routerResult.getOriginalRequest());
                            callback.onSuccess(routerResult, result);
                        }
                    });
                }

                @Override
                @MainThread
                public void onError(@NonNull RouterErrorResult errorResult) {
                    super.onError(errorResult);
                    Help.removeRequestCode(errorResult.getOriginalRequest());
                    callback.onError(errorResult);
                }

                @Override
                @MainThread
                public void onCancel(@NonNull RouterRequest originalRequest) {
                    super.onCancel(originalRequest);
                    rxFragment.removeActivityResultConsumer(originalRequest);
                    Help.removeRequestCode(originalRequest);
                    callback.onCancel(originalRequest);
                }

            });
            // 现在�?�以检测 requestCode 是�?��?�?,除了 RxRouter 之外的地方使用�?�一个 requestCode 是�?�以的
            // 因为 RxRouter 的 requestCode 是直接�?�?� RouterRxFragment 使用的
            // 其他地方是用�?到 RouterRxFragment,所以�?�以�?�?
            boolean isExist = Help.isExist(finalNavigationDisposable.originalRequest());
            if (isExist) { // 如果存在直接返回错误给 callback
                throw new NavigationFailException("request&result code is " +
                        finalNavigationDisposable.originalRequest().requestCode + " is exist and " +
                        "uri is " + finalNavigationDisposable.originalRequest().uri.toString());
            } else {
                Help.addRequestCode(finalNavigationDisposable.originalRequest());
            }
            return finalNavigationDisposable;
        } catch (Exception e) {
            callback.onError(new RouterErrorResult(e));
            if (finalNavigationDisposable != null) {
                // �?�消这个路由
                finalNavigationDisposable.cancel();
                finalNavigationDisposable = null;
            }
            return Router.emptyNavigationDisposable;
        }

    }

    /**
     * @return 返回的对象有�?�能是一个空实现对象 {@link Router#emptyNavigationDisposable}
     */
    @NonNull
    public NavigationDisposable navigate() {
        return navigate(null);
    }

    /**
     * 执行跳转的具体逻辑
     * 返回值�?�?�以为空,是为了使用的时候更加的顺溜,�?用判断空
     *
     * @param callback 回调
     * @return 返回的对象有�?�能是一个空实现对象 {@link Router#emptyNavigationDisposable},�?�以�?�消路由或者获�?�原始request对象
     */
    @AnyThread
    @NonNull
    public synchronized NavigationDisposable navigate(@Nullable final Callback callback) {
        // 构建请求对象
        RouterRequest originalRequest = null;
        try {
            // 如果用户没填写 Context 或者 Fragment 默认使用 Application
            useDefaultApplication();
            // 路由�?的检查
            onCheck();
            // 标记这个 builder 已�?�?能使用了
            isFinish = true;
            // 生�?路由请求对象
            originalRequest = build();
            // 创建整个拦截器到最终跳转需�?使用的 Callback
            final InterceptorCallback interceptorCallback = new InterceptorCallback(originalRequest, callback);
            // Fragment 的销�?的自动�?�消
            if (originalRequest.fragment != null) {
                Router.mNavigationDisposableList.add(interceptorCallback);
            }
            // Activity 的自动�?�消
            if (Utils.getActivityFromContext(originalRequest.context) != null) {
                Router.mNavigationDisposableList.add(interceptorCallback);
            }
            // 真正的去执行路由
            realNavigate(originalRequest, customInterceptors, interceptorCallback);
            // 返回对象
            return interceptorCallback;
        } catch (Exception e) { // �?�生路由错误的时候
            RouterErrorResult errorResult = new RouterErrorResult(originalRequest, e);
            RouterUtil.errorCallback(callback, errorResult);
        } finally {
            // 释放资�?
            context = null;
            fragment = null;
            scheme = null;
            url = null;
            host = null;
            path = null;
            requestCode = null;
            queryMap = null;
            bundle = null;
            intentConsumer = null;
            beforJumpAction = null;
            afterJumpAction = null;
        }
        return Router.emptyNavigationDisposable;
    }

    /**
     * 真正的执行路由
     *
     * @param originalRequest    最原始的请求对象
     * @param customInterceptors 自定义的拦截器
     * @param callback           回调对象
     */
    @AnyThread
    private static void realNavigate(@NonNull final RouterRequest originalRequest,
                                     @Nullable List<Object> customInterceptors,
                                     @NonNull RouterInterceptor.Callback callback) {

        // 拿到共有的拦截器
        List<RouterInterceptor> publicInterceptors = InterceptorCenter.getInstance()
                .getGlobalInterceptorList();
        // 自定义拦截器,�?始化拦截器的个数 8 个够用应该�?会�?常扩容
        final List<RouterInterceptor> currentInterceptors = new ArrayList(8);
        // 添加内置拦截器,目�?就一个内置拦截器,而且必须在最�?�?�,因为这个拦截器内部有一个时间的记录
        // �?�?一秒内就�?�能打开一个相�?�的界�?�
        currentInterceptors.add(OpenOnceInterceptor.getInstance());
        // 添加共有拦截器
        currentInterceptors.addAll(publicInterceptors);
        // 添加自定义拦截器
        addCustomInterceptors(originalRequest, customInterceptors, currentInterceptors);
        // 扫尾拦截器,内部会添加目标�?求执行的拦截器和真正执行跳转的拦截器
        currentInterceptors.add(new RouterInterceptor() {
            @Override
            public void intercept(Chain nextChain) throws Exception {
                // 这个地�?��?执行的拦截器,这里�?�的时候一定�?注�?了,�?能拿最原始的那个 request,因为上�?�的拦截器都能更改 request,
                // 导致最终跳转的界�?�和你拿到的拦截器�?匹�?,所以这里一定是拿上一个拦截器传给你的 request 对象
                List<RouterInterceptor> targetInterceptors = RouterCenter.getInstance().interceptors(nextChain.request().uri);
                if (!targetInterceptors.isEmpty()) {
                    currentInterceptors.addAll(targetInterceptors);
                }
                // 真正的执行跳转的拦截器
                currentInterceptors.add(new RealInterceptor(originalRequest));
                // 执行下一个拦截器,正好是上�?�代�?添加的拦截器
                nextChain.proceed(nextChain.request());
            }
        });
        // 创建执行器
        final RouterInterceptor.Chain chain = new InterceptorChain(currentInterceptors, 0, originalRequest,
                callback);
        // 执行
        chain.proceed(originalRequest);

    }

    /**
     * 添加自定义的拦截器
     *
     * @param originalRequest
     * @param customInterceptors
     * @param currentInterceptors
     */
    private static void addCustomInterceptors(@NonNull RouterRequest originalRequest,
                                              @Nullable List<Object> customInterceptors,
                                              List<RouterInterceptor> currentInterceptors) {
        if (customInterceptors == null) {
            return;
        }
        for (Object customInterceptor : customInterceptors) {
            if (customInterceptor instanceof RouterInterceptor) {
                currentInterceptors.add((RouterInterceptor) customInterceptor);
            } else if (customInterceptor instanceof Class) {
                RouterInterceptor interceptor = RouterInterceptorCache.getInterceptorByClass((Class<? extends RouterInterceptor>) customInterceptor);
                if (interceptor == null) {
                    throw new InterceptorNotFoundException("can't find the interceptor and it's className is " + (Class) customInterceptor + ",target url is " + originalRequest.uri.toString());
                } else {
                    currentInterceptors.add(interceptor);
                }
            } else if (customInterceptor instanceof String) {
                RouterInterceptor interceptor = InterceptorCenter.getInstance().getByName((String) customInterceptor);
                if (interceptor == null) {
                    throw new InterceptorNotFoundException("can't find the interceptor and it's name is " + (String) customInterceptor + ",target url is " + originalRequest.uri.toString());
                } else {
                    currentInterceptors.add(interceptor);
                }
            }
        }
    }

    /**
     * 这个拦截器的 Callback 是所有拦截器执行过程中会使用的一个 Callback,这是唯一的一个,�?个拦截器对象拿到的此对象都是一样的
     */
    private static class InterceptorCallback implements NavigationDisposable, RouterInterceptor.Callback {

        /**
         * 用户的回调
         */
        @Nullable
        private Callback mCallback;

        /**
         * 最原始的请求,用户构建的,�?会更改的
         */
        @NonNull
        private final RouterRequest mOriginalRequest;

        /**
         * 标记是�?�完�?,出错或者�?功都算是完�?了,�?能�?继续调用了
         */
        private boolean isComplete = false;

        /**
         * �?�消
         */
        private boolean isCanceled;

        /**
         * 标记这次路由请求是�?�完毕
         *
         * @return
         */
        private boolean isEnd() {
            return isComplete || isCanceled;
        }

        public InterceptorCallback(@NonNull RouterRequest originalRequest,
                                   @Nullable Callback callback) {
            this.mOriginalRequest = originalRequest;
            this.mCallback = callback;
        }

        @Override
        public void onSuccess(RouterResult result) {
            synchronized (this) {
                if (isEnd()) {
                    return;
                }
                isComplete = true;
                RouterUtil.successCallback(mCallback, result);
            }
        }

        @Override
        public void onError(Throwable error) {
            synchronized (this) {
                if (isEnd()) {
                    return;
                }
                isComplete = true;
                RouterErrorResult errorResult = new RouterErrorResult(mOriginalRequest, error);
                RouterUtil.errorCallback(mCallback, errorResult);
            }
        }

        @Override
        public boolean isComplete() {
            synchronized (this) {
                return isComplete;
            }
        }

        @Override
        public boolean isCanceled() {
            synchronized (this) {
                return isCanceled;
            }
        }

        @NonNull
        @Override
        public RouterRequest originalRequest() {
            return mOriginalRequest;
        }

        @Override
        @AnyThread
        public void cancel() {
            synchronized (this) {
                if (isEnd()) {
                    return;
                }
                // 标记�?�消�?功
                isCanceled = true;
                RouterUtil.cancelCallback(mOriginalRequest, mCallback);
            }
        }
    }

    /**
     * 实现拦截器列表中的最�?�一环,内部去执行了跳转的代�?,并且切�?�了线程执行,当�?线程会�?��?
     */
    private static class RealInterceptor implements RouterInterceptor {

        @NonNull
        private final RouterRequest mOriginalRequest;

        public RealInterceptor(@NonNull RouterRequest originalRequest) {
            mOriginalRequest = originalRequest;
        }

        /**
         * @param chain 拦截器执行连接器
         * @throws Exception
         */
        @Override
        @MainThread
        public void intercept(final Chain chain) throws Exception {
            try {
                // 这个 request 对象已�?�?是最原始的了,但是�?�能是最原始的,就看拦截器是�?�更改了这个对象了
                RouterRequest finalRequest = chain.request();
                if (finalRequest.beforJumpAction != null) {
                    finalRequest.beforJumpAction.run();
                }
                // 真正执行跳转的逻辑
                RouterCenter.getInstance().openUri(finalRequest);
                if (finalRequest.afterJumpAction != null) {
                    finalRequest.afterJumpAction.run();
                }
                chain.callback().onSuccess(new RouterResult(mOriginalRequest, finalRequest));
            } catch (Exception e) {
                chain.callback().onError(e);
            }
        }

    }

    /**
     * 拦截器多个连接�?�走的执行器,�?代�?�?��?于 OkHTTP
     * 这个原�?�就是,本身是一个 执行器 (Chain),当你调用 proceed 方法的时候,会创建下一个拦截器的执行对象
     * 然�?�调用当�?拦截器的 intercept 方法
     */
    private static class InterceptorChain implements RouterInterceptor.Chain {

        /**
         * �?一个拦截器执行器 {@link RouterInterceptor.Chain}
         * 都会有上一个拦截器给的 request 对象或者�?始化的一个 request,用于在下一个拦截器
         * 中获�?�到 request 对象,并且支�?拦截器自定义修改 request 对象或者直接创建一个新的传给下一个拦截器执行器
         */
        @NonNull
        private final RouterRequest mRequest;

        /**
         * 这个是拦截器的回调,这个用户�?能自定义,一直都是一个对象
         */
        @NonNull
        private final RouterInterceptor.Callback mCallback;

        /**
         * 拦截器列表,所有�?执行的拦截器列表
         */
        @NonNull
        private final List<RouterInterceptor> mInterceptors;

        /**
         * 拦截器的下标
         */
        private final int mIndex;

        /**
         * 调用的次数,如果超过1次就�?�相应的错误处�?�
         */
        private int calls;

        /**
         * @param interceptors
         * @param index
         * @param request      第一次这个对象是�?需�?的
         * @param callback
         */
        public InterceptorChain(@NonNull List<RouterInterceptor> interceptors, int index,
                                @NonNull RouterRequest request, @NonNull RouterInterceptor.Callback callback) {
            this.mInterceptors = interceptors;
            this.mIndex = index;
            this.mRequest = request;
            this.mCallback = callback;
        }

        @Override
        public RouterRequest request() {
            // 第一个拦截器的
            return mRequest;
        }

        @Override
        public RouterInterceptor.Callback callback() {
            return mCallback;
        }

        @Override
        public void proceed(final RouterRequest request) {
            proceed(request, mCallback);
        }

        private void proceed(@NonNull final RouterRequest request, @NonNull final RouterInterceptor.Callback callback) {
            // ui 线程上执行
            Utils.postActionToMainThreadAnyway(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (callback().isComplete() || callback().isCanceled()) {
                            return;
                        }
                        if (request == null) {
                            callback().onError(new NavigationFailException("the reqest is null,you can't call 'proceed' method with null reqest,such as 'chain.proceed(null)'"));
                            return;
                        }
                        ++calls;
                        if (mIndex >= mInterceptors.size()) {
                            callback().onError(new NavigationFailException(new IndexOutOfBoundsException(
                                    "size = " + mInterceptors.size() + ",index = " + mIndex)));
                        } else if (calls > 1) { // 调用了两次
                            callback().onError(new NavigationFailException(
                                    "interceptor " + mInterceptors.get(mIndex - 1)
                                            + " must call proceed() exactly once"));
                        } else {
                            // 当拦截器最�?�一个的时候,就�?是这个类了,是 RealInterceptor 了
                            InterceptorChain next = new InterceptorChain(mInterceptors, mIndex + 1,
                                    request, callback);
                            // current Interceptor
                            RouterInterceptor interceptor = mInterceptors.get(mIndex);
                            // 用户自定义的部分,必须在主线程
                            interceptor.intercept(next);
                        }
                    } catch (Exception e) {
                        callback().onError(e);
                    }
                }
            });
        }
    }

    /**
     * 一些帮助方法
     */
    private static class Help {

        /**
         * 和{@link RouterRxFragment} �?套使用
         */
        private static Set<String> mRequestCodeSet = new HashSet<>();

        private static Random r = new Random();

        /**
         * �?机生�?一个 requestCode,调用这个方法的 requestCode 是 {@link Navigator#RANDOM_REQUSET_CODE}
         *
         * @return [1, 256]
         */
        @NonNull
        public static RouterRequest randomlyGenerateRequestCode(@NonNull RouterRequest request) {
            Utils.checkNullPointer(request, "request");
            // 如果�?是想�?�?机生�?,就直接返回
            if (!Navigator.RANDOM_REQUSET_CODE.equals(request.requestCode)) {
                return request;
            }
            // 转化为构建对象
            RouterRequest.Builder requestBuilder = request.toBuilder();
            int generateRequestCode = r.nextInt(256) + 1;
            // 如果生�?的这个 requestCode 存在,就�?新生�?
            while (isExist(Utils.getActivityFromContext(requestBuilder.context), requestBuilder.fragment, generateRequestCode)) {
                generateRequestCode = r.nextInt(256) + 1;
            }
            return requestBuilder.requestCode(generateRequestCode).build();
        }

        /**
         * 检测�?�一个 Fragment 或者 Activity �?�起的多个路由 request 中的 requestCode 是�?�存在了
         *
         * @param request 路由请求对象
         * @return
         */
        public static boolean isExist(@Nullable RouterRequest request) {
            if (request == null || request.requestCode == null) {
                return false;
            }
            // 这个 Context 关�?�的 Activity,用requestCode 去拿数�?�的情况下
            // Context 必须是一个 Activity 或者 内部的 baseContext 是 Activity
            Activity act = Utils.getActivityFromContext(request.context);
            // 这个requestCode�?会为空, 用这个方法的地方是必须填写 requestCode 的
            return isExist(act, request.fragment, request.requestCode);
        }

        public static boolean isExist(@Nullable Activity act, @Nullable Fragment fragment, @NonNull Integer requestCode) {
            if (act != null) {
                return mRequestCodeSet.contains(act.getClass().getName() + requestCode);
            } else if (fragment != null) {
                return mRequestCodeSet.contains(fragment.getClass().getName() + requestCode);
            }
            return false;
        }

        /**
         * 添加一个路由请求的 requestCode
         *
         * @param request 路由请求对象
         */
        public static void addRequestCode(@Nullable RouterRequest request) {
            if (request == null || request.requestCode == null) {
                return;
            }
            Integer requestCode = request.requestCode;
            // 这个 Context 关�?�的 Activity,用requestCode 去拿数�?�的情况下
            // Context 必须是一个 Activity 或者 内部的 baseContext 是 Activity
            Activity act = Utils.getActivityFromContext(request.context);
            if (act != null) {
                mRequestCodeSet.add(act.getClass().getName() + requestCode);
            } else if (request.fragment != null) {
                mRequestCodeSet.add(request.fragment.getClass().getName() + requestCode);
            }
        }

        /**
         * 移除一个路由请求的 requestCode
         *
         * @param request 路由请求对象
         */
        public static void removeRequestCode(@Nullable RouterRequest request) {
            if (request == null || request.requestCode == null) {
                return;
            }
            Integer requestCode = request.requestCode;
            // 这个 Context 关�?�的 Activity,用requestCode 去拿数�?�的情况下
            // Context 必须是一个 Activity 或者 内部的 baseContext 是 Activity
            Activity act = Utils.getActivityFromContext(request.context);
            if (act != null) {
                mRequestCodeSet.remove(act.getClass().getName() + requestCode);
            } else if (request.fragment != null) {
                mRequestCodeSet.remove(request.fragment.getClass().getName() + requestCode);
            }
        }

    }


}
