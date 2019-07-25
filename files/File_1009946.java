package com.xiaojinzi.component.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.xiaojinzi.component.bean.ActivityResult;
import com.xiaojinzi.component.error.UnknowException;
import com.xiaojinzi.component.error.ignore.ActivityResultException;
import com.xiaojinzi.component.error.ignore.InterceptorNotFoundException;
import com.xiaojinzi.component.error.ignore.NavigationFailException;
import com.xiaojinzi.component.error.ignore.TargetActivityNotFoundException;
import com.xiaojinzi.component.support.Action;
import com.xiaojinzi.component.support.CallbackAdapter;
import com.xiaojinzi.component.support.NavigationDisposable;
import com.xiaojinzi.component.support.Utils;

import java.io.Serializable;
import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 使用这个�?�以结�?� RxJava 中的{@link io.reactivex.Single} 使用,会很方便
 * <p>
 * time   : 2018/11/03
 *
 * @author : xiaojinzi 30212
 */
public class RxRouter extends Router {

    /**
     * requestCode 如果等于这个值,就表示是�?机生�?的
     * 从 1-256 中�?机生�?一个,如果生�?的正好是目�?正在用的,会�?新生�?一个
     */
    public static final Integer RANDOM_REQUSET_CODE = Integer.MIN_VALUE;

    public static final String TAG = "RxRouter";

    public static Builder with() {
        return new Builder();
    }

    /**
     * 这个方法父类也有一个�?��?的,但是父类返回的是 {@link Navigator} 而这个返回的是
     * {@link RxRouter.Builder}
     *
     * @param context
     * @return
     */
    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    /**
     * 这个方法父类也有一个�?��?的,但是父类返回的是 {@link Navigator} 而这个返回的是
     * {@link RxRouter.Builder}
     *
     * @param fragment
     * @return
     */
    public static Builder with(@NonNull Fragment fragment) {
        return new Builder(fragment);
    }

    public static class Builder extends Navigator {

        private Builder() {
            super();
        }

        private Builder(@NonNull Context context) {
            super(context);
        }

        private Builder(@NonNull Fragment fragment) {
            super(fragment);
        }

        @Override
        public Builder beforJumpAction(@NonNull Action action) {
            return (Builder) super.beforJumpAction(action);
        }

        @Override
        public Builder afterJumpAction(@NonNull Action action) {
            return (Builder) super.afterJumpAction(action);
        }

        @Override
        public Builder intentConsumer(@NonNull com.xiaojinzi.component.support.Consumer<Intent> intentConsumer) {
            return (Builder) super.intentConsumer(intentConsumer);
        }

        @Override
        public Builder addIntentFlags(@Nullable Integer... flags) {
            return (Builder) super.addIntentFlags(flags);
        }

        @Override
        public Builder addIntentCategories(@Nullable String... categories) {
            return (Builder) super.addIntentCategories(categories);
        }

        @Override
        public Builder interceptors(@NonNull RouterInterceptor... interceptors) {
            return (Builder) super.interceptors(interceptors);
        }

        @Override
        public Builder interceptors(@NonNull Class<? extends RouterInterceptor>... interceptors) {
            return (Builder) super.interceptors(interceptors);
        }

        @Override
        public Builder interceptorNames(@NonNull String... interceptors) {
            return (Builder) super.interceptorNames(interceptors);
        }

        @Override
        public Builder url(@NonNull String url) {
            return (Builder) super.url(url);
        }

        @Override
        public Builder scheme(@NonNull String scheme) {
            return (Builder) super.scheme(scheme);
        }

        @Override
        public Builder hostAndPath(@NonNull String hostAndPath) {
            return (Builder) super.hostAndPath(hostAndPath);
        }

        @Override
        public Builder host(@NonNull String host) {
            return (Builder) super.host(host);
        }

        @Override
        public Builder path(@NonNull String path) {
            return (Builder) super.path(path);
        }

        @Override
        public Builder requestCode(@Nullable Integer requestCode) {
            return (Builder) super.requestCode(requestCode);
        }

        @Override
        public Builder options(@Nullable Bundle options) {
            return (Builder) super.options(options);
        }

        /**
         * requestCode 会�?机的生�?
         *
         * @return
         */
        public Builder requestCodeRandom() {
            return requestCode(RANDOM_REQUSET_CODE);
        }

        @Override
        public Builder putBundle(@NonNull String key, @Nullable Bundle bundle) {
            return (Builder) super.putBundle(key, bundle);
        }

        @Override
        public Builder putAll(@NonNull Bundle bundle) {
            return (Builder) super.putAll(bundle);
        }

        @Override
        public Builder putCharSequence(@NonNull String key, @Nullable CharSequence value) {
            return (Builder) super.putCharSequence(key, value);
        }

        @Override
        public Builder putCharSequenceArray(@NonNull String key, @Nullable CharSequence[] value) {
            return (Builder) super.putCharSequenceArray(key, value);
        }

        @Override
        public Builder putCharSequenceArrayList(@NonNull String key, @Nullable ArrayList<CharSequence> value) {
            return (Builder) super.putCharSequenceArrayList(key, value);
        }

        @Override
        public Builder putByte(@NonNull String key, @Nullable byte value) {
            return (Builder) super.putByte(key, value);
        }

        @Override
        public Builder putByteArray(@NonNull String key, @Nullable byte[] value) {
            return (Builder) super.putByteArray(key, value);
        }

        @Override
        public Builder putChar(@NonNull String key, @Nullable char value) {
            return (Builder) super.putChar(key, value);
        }

        @Override
        public Builder putCharArray(@NonNull String key, @Nullable char[] value) {
            return (Builder) super.putCharArray(key, value);
        }

        @Override
        public Builder putBoolean(@NonNull String key, @Nullable boolean value) {
            return (Builder) super.putBoolean(key, value);
        }

        @Override
        public Builder putBooleanArray(@NonNull String key, @Nullable boolean[] value) {
            return (Builder) super.putBooleanArray(key, value);
        }

        @Override
        public Builder putString(@NonNull String key, @Nullable String value) {
            return (Builder) super.putString(key, value);
        }

        @Override
        public Builder putStringArray(@NonNull String key, @Nullable String[] value) {
            return (Builder) super.putStringArray(key, value);
        }

        @Override
        public Builder putStringArrayList(@NonNull String key, @Nullable ArrayList<String> value) {
            return (Builder) super.putStringArrayList(key, value);
        }

        @Override
        public Builder putShort(@NonNull String key, @Nullable short value) {
            return (Builder) super.putShort(key, value);
        }

        @Override
        public Builder putShortArray(@NonNull String key, @Nullable short[] value) {
            return (Builder) super.putShortArray(key, value);
        }

        @Override
        public Builder putInt(@NonNull String key, @Nullable int value) {
            return (Builder) super.putInt(key, value);
        }

        @Override
        public Builder putIntArray(@NonNull String key, @Nullable int[] value) {
            return (Builder) super.putIntArray(key, value);
        }

        @Override
        public Builder putIntegerArrayList(@NonNull String key, @Nullable ArrayList<Integer> value) {
            return (Builder) super.putIntegerArrayList(key, value);
        }

        @Override
        public Builder putLong(@NonNull String key, @Nullable long value) {
            return (Builder) super.putLong(key, value);
        }

        @Override
        public Builder putLongArray(@NonNull String key, @Nullable long[] value) {
            return (Builder) super.putLongArray(key, value);
        }

        @Override
        public Builder putFloat(@NonNull String key, @Nullable float value) {
            return (Builder) super.putFloat(key, value);
        }

        @Override
        public Builder putFloatArray(@NonNull String key, @Nullable float[] value) {
            return (Builder) super.putFloatArray(key, value);
        }

        @Override
        public Builder putDouble(@NonNull String key, @Nullable double value) {
            return (Builder) super.putDouble(key, value);
        }

        @Override
        public Builder putDoubleArray(@NonNull String key, @Nullable double[] value) {
            return (Builder) super.putDoubleArray(key, value);
        }

        @Override
        public Builder putParcelable(@NonNull String key, @Nullable Parcelable value) {
            return (Builder) super.putParcelable(key, value);
        }

        @Override
        public Builder putParcelableArray(@NonNull String key, @Nullable Parcelable[] value) {
            return (Builder) super.putParcelableArray(key, value);
        }

        @Override
        public Builder putParcelableArrayList(@NonNull String key, @Nullable ArrayList<? extends Parcelable> value) {
            return (Builder) super.putParcelableArrayList(key, value);
        }

        @Override
        public Builder putSparseParcelableArray(@NonNull String key, @Nullable SparseArray<? extends Parcelable> value) {
            return (Builder) super.putSparseParcelableArray(key, value);
        }

        @Override
        public Builder putSerializable(@NonNull String key, @Nullable Serializable value) {
            return (Builder) super.putSerializable(key, value);
        }

        @Override
        public Builder query(@NonNull String queryName, @Nullable String queryValue) {
            return (Builder) super.query(queryName, queryValue);
        }

        @Override
        public Builder query(@NonNull String queryName, boolean queryValue) {
            return (Builder) super.query(queryName, queryValue);
        }

        @Override
        public Builder query(@NonNull String queryName, byte queryValue) {
            return (Builder) super.query(queryName, queryValue);
        }

        @Override
        public Builder query(@NonNull String queryName, int queryValue) {
            return (Builder) super.query(queryName, queryValue);
        }

        @Override
        public Builder query(@NonNull String queryName, float queryValue) {
            return (Builder) super.query(queryName, queryValue);
        }

        @Override
        public Builder query(@NonNull String queryName, long queryValue) {
            return (Builder) super.query(queryName, queryValue);
        }

        @Override
        public Builder query(@NonNull String queryName, double queryValue) {
            return (Builder) super.query(queryName, queryValue);
        }

        /**
         * 一个�?�以拿到 Intent 的 Observable
         *
         * @return
         * @see #activityResultCall()
         */
        public Single<Intent> intentCall() {
            return activityResultCall()
                    .map(new Function<ActivityResult, Intent>() {
                        @Override
                        public Intent apply(ActivityResult activityResult) throws Exception {
                            return activityResult.intentCheckAndGet();
                        }
                    });
        }

        /**
         * 拿到 resultCode 的 Observable
         *
         * @return
         */
        public Single<Integer> resultCodeCall() {
            return activityResultCall()
                    .map(new Function<ActivityResult, Integer>() {
                        @Override
                        public Integer apply(ActivityResult activityResult) throws Exception {
                            return activityResult.resultCode;
                        }
                    });
        }

        /**
         * requestCode 一定是相�?�的,resultCode 如果匹�?了就剩下 Intent �?�数了
         * 这个方法�?会给你 Intent 对象,�?�会给你是�?� resultCode 匹�?�?功了
         * 那么
         *
         * @param expectedResultCode 期望的 resultCode 的值
         * @return 返回一个完�?状�?的 Observable
         * @see #activityResultCall()
         */
        public Completable resultCodeMatchCall(final int expectedResultCode) {
            return activityResultCall()
                    .doOnSuccess(new Consumer<ActivityResult>() {
                        @Override
                        public void accept(ActivityResult activityResult) throws Exception {
                            if (activityResult.resultCode != expectedResultCode) {
                                throw new ActivityResultException("the resultCode is not matching " + expectedResultCode);
                            }
                        }
                    })
                    .ignoreElement();
        }

        /**
         * 这个方法�?仅�?�以匹�? resultCode,还�?�以拿到 Intent,当�?匹�?或者 Intent 为空的时候都会报错哦
         *
         * @param expectedResultCode 期望的 resultCode 的值
         * @return 返回一个�?�射 Single 的 Observable
         * @see #activityResultCall()
         */
        public Single<Intent> intentResultCodeMatchCall(final int expectedResultCode) {
            return activityResultCall()
                    .map(new Function<ActivityResult, Intent>() {
                        @Override
                        public Intent apply(ActivityResult activityResult) throws Exception {
                            return activityResult.intentWithResultCodeCheckAndGet(expectedResultCode);
                        }
                    });
        }

        /**
         * 一个�?�以拿到 ActivityResult 的路由 Observable
         *
         * @return
         */
        public Single<ActivityResult> activityResultCall() {
            return Single.create(new SingleOnSubscribe<ActivityResult>() {
                @Override
                public void subscribe(final SingleEmitter<ActivityResult> emitter) throws Exception {
                    if (emitter.isDisposed()) {
                        return;
                    }
                    final NavigationDisposable navigationDisposable = navigateForResult(new BiCallback.BiCallbackAdapter<ActivityResult>() {
                        @Override
                        public void onSuccess(@NonNull RouterResult result, @NonNull ActivityResult activityResult) {
                            super.onSuccess(result, activityResult);
                            if (emitter.isDisposed()) {
                                return;
                            }
                            emitter.onSuccess(activityResult);
                        }

                        @Override
                        public void onError(@NonNull RouterErrorResult errorResult) {
                            super.onError(errorResult);
                            if (emitter.isDisposed()) {
                                return;
                            }
                            RxHelp.onErrorSolve(emitter, errorResult.getError());
                        }

                        @Override
                        public void onCancel(@NonNull RouterRequest originalRequest) {
                            super.onCancel(originalRequest);
                        }
                    });
                    emitter.setCancellable(new Cancellable() {
                        @Override
                        public void cancel() throws Exception {
                            navigationDisposable.cancel();
                        }
                    });
                }
            });
        }

        /**
         * 一个完�?状�?的 Observable 的路由跳转
         *
         * @return
         */
        public Completable call() {
            return Completable.create(new CompletableOnSubscribe() {
                @Override
                public void subscribe(final CompletableEmitter emitter) throws Exception {
                    if (emitter.isDisposed()) {
                        return;
                    }
                    // 导航拿到 NavigationDisposable 对象
                    // �?�能是一个 空实现,这些个回调都是回调在主线程的
                    final NavigationDisposable navigationDisposable = navigate(new CallbackAdapter() {
                        @Override
                        @MainThread
                        public void onSuccess(@NonNull RouterResult routerResult) {
                            super.onSuccess(routerResult);
                            if (emitter != null && !emitter.isDisposed()) {
                                emitter.onComplete();
                            }
                        }

                        @Override
                        @MainThread
                        public void onError(@NonNull RouterErrorResult errorResult) {
                            super.onError(errorResult);
                            RxHelp.onErrorSolve(emitter, errorResult.getError());
                        }
                    });
                    // 设置�?�消
                    emitter.setCancellable(new Cancellable() {
                        @Override
                        public void cancel() throws Exception {
                            navigationDisposable.cancel();
                        }
                    });
                }
            });
        }

    }

    /**
     * 一些帮助方法
     */
    private static class RxHelp {

        /**
         * 错误处�?�
         */
        private static void onErrorSolve(@NonNull final SingleEmitter<? extends Object> emitter, @NonNull Throwable e) {
            if (e instanceof InterceptorNotFoundException) {
                onErrorEmitter(emitter, e);
            } else if (e instanceof NavigationFailException) {
                onErrorEmitter(emitter, e);
            } else if (e instanceof TargetActivityNotFoundException) {
                onErrorEmitter(emitter, e);
            } else if (e instanceof ActivityResultException) {
                onErrorEmitter(emitter, e);
            } else {
                onErrorEmitter(emitter, new UnknowException(e));
            }
        }

        /**
         * 错误处�?�
         */
        private static void onErrorSolve(@NonNull CompletableEmitter emitter, @NonNull Throwable e) {
            if (e instanceof InterceptorNotFoundException) {
                onErrorEmitter(emitter, e);
            } else if (e instanceof NavigationFailException) {
                onErrorEmitter(emitter, e);
            } else if (e instanceof TargetActivityNotFoundException) {
                onErrorEmitter(emitter, e);
            } else if (e instanceof ActivityResultException) {
                onErrorEmitter(emitter, e);
            } else {
                onErrorEmitter(emitter, new UnknowException(e));
            }
        }

        /**
         * �?�射错误,目�?这些个�?�射错误都是为了 {@link RxRouter} 写的,�?�射的错误和正确的 item 被�?�射都应该
         * 最终�?�射在主线程
         *
         * @param emitter
         * @param e
         */
        private static void onErrorEmitter(@MainThread final SingleEmitter<? extends Object> emitter,
                                           @NonNull final Throwable e) {
            if (emitter == null || emitter.isDisposed()) {
                return;
            }
            if (Utils.isMainThread()) {
                emitter.onError(e);
            } else {
                Utils.postActionToMainThreadAnyway(new Runnable() {
                    @Override
                    public void run() {
                        emitter.onError(e);
                    }
                });
            }
        }

        /**
         * �?�射错误,目�?这些个�?�射错误都是为了 {@link RxRouter} 写的,�?�射的错误和正确的 item 被�?�射都应该
         * 最终�?�射在主线程
         *
         * @param emitter
         * @param e
         */
        private static void onErrorEmitter(@MainThread final CompletableEmitter emitter,
                                           @NonNull final Throwable e) {
            if (emitter == null || emitter.isDisposed()) {
                return;
            }
            if (Utils.isMainThread()) {
                emitter.onError(e);
            } else {
                Utils.postActionToMainThreadAnyway(new Runnable() {
                    @Override
                    public void run() {
                        emitter.onError(e);
                    }
                });
            }
        }

    }

}
