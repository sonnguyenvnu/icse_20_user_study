package com.sankuai.waimai.router.demo.advanced.location;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.sankuai.waimai.router.core.UriCallback;
import com.sankuai.waimai.router.core.UriInterceptor;
import com.sankuai.waimai.router.core.UriRequest;
import com.sankuai.waimai.router.demo.lib2.CustomUriResult;
import com.sankuai.waimai.router.demo.lib2.DialogUtils;
import com.sankuai.waimai.router.demo.lib2.ToastUtils;
import com.sankuai.waimai.router.demo.lib2.advanced.services.DemoServiceManager;
import com.sankuai.waimai.router.demo.lib2.advanced.services.ILocationService;

/**
 * 用于定�?的Interceptor
 *
 * Created by jzj on 2018/3/23.
 */

public class LocationInterceptor implements UriInterceptor {

    @Override
    public void intercept(@NonNull final UriRequest request, @NonNull final UriCallback callback) {
        ILocationService locationService = DemoServiceManager.getLocationService();
        if (locationService.hasLocation()) {
            // 已有定�?信�?�，�?�?�拦截，继续跳转
            callback.onNext();
            return;
        }
        final Context context = request.getContext();
        if (!(context instanceof Activity) || ((Activity) context).isFinishing()) {
            // 异常情况，忽略
            callback.onNext();
            return;
        }
        final ProgressDialog dialog = DialogUtils.showProgress(context, "定�?中...");
        locationService.startLocation(new ILocationService.LocationListener() {
            @Override
            public void onSuccess() {
                // 定�?�?功，继续跳转
                ToastUtils.showToast(context, "定�?�?功");
                DialogUtils.dismiss(dialog);
                callback.onNext();
            }

            @Override
            public void onFailure() {
                // 定�?失败，�?跳转
                ToastUtils.showToast(context, "定�?失败");
                DialogUtils.dismiss(dialog);
                callback.onComplete(CustomUriResult.CODE_LOCATION_FAILURE);
            }
        });
    }
}
