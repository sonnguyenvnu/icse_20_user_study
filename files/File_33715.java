package com.example.jingbin.cloudreader.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.jingbin.cloudreader.app.CloudReaderApplication;

/**
 * Created by Administrator on 2015/10/19.
 */
public class DensityUtil {

    /**
     * 根�?�手机的分辨率从 dp 的�?��? 转�?为 px(�?素)
     */
    public static int dip2px(float dpValue) {
        final float scale = CloudReaderApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根�?�手机的分辨率从 px(�?素) 的�?��? 转�?为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = CloudReaderApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 设置�?个View的margin
     *
     * @param view   需�?设置的view
     * @param isDp   需�?设置的数值是�?�为DP
     * @param left   左边�?
     * @param right  �?�边�?
     * @param top    上边�?
     * @param bottom 下边�?
     * @return
     */
    public static ViewGroup.LayoutParams setViewMargin(View view, boolean isDp, int left, int right, int top, int bottom) {
        if (view == null) {
            return null;
        }

        int leftPx = left;
        int rightPx = right;
        int topPx = top;
        int bottomPx = bottom;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获�?�view的margin设置�?�数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //�?存在时创建一个新的�?�数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }

        //根�?�DP与PX转�?�计算值
        if (isDp) {
            leftPx = dip2px(left);
            rightPx = dip2px(right);
            topPx = dip2px(top);
            bottomPx = dip2px(bottom);
        }
        //设置margin
        marginParams.setMargins(leftPx, topPx, rightPx, bottomPx);
        view.setLayoutParams(marginParams);
        view.requestLayout();
        return marginParams;
    }

    /**
     * 通过比例得到高度
     *
     * @param bili         图片比例
     * @param type         1:外层 LinearLayout 2：外层 RelativeLayout
     * @param marginLR     左�?�的dp
     * @param marginTop    上�?�的dp
     * @param marginBottom 下�?�的dp
     */
    public static void formatHeight(View imageView, float bili, int type, int marginLR, int marginTop, int marginBottom) {
        WindowManager wm = (WindowManager) CloudReaderApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = (int) (width / bili);
        if (type == 1) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            imageView.setLayoutParams(lp);
        } else if (type == 2) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            imageView.setLayoutParams(lp);
        } else {
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height);
            imageView.setLayoutParams(lp);
        }

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        layoutParams.setMargins(dip2px(marginLR), dip2px(marginTop), dip2px(marginLR), dip2px(marginBottom));
    }

    /**
     * 通过比例设置图片的高度
     *
     * @param width 图片的宽
     * @param bili  图片比例
     * @param type  1:外层 LinearLayout 2：外层 RelativeLayout
     */
    public static void formatHeight(View imageView, int width, float bili, int type) {
        int height = (int) (width / bili);
        if (type == 1) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            imageView.setLayoutParams(lp);
        } else if (type == 2) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            imageView.setLayoutParams(lp);
        } else {
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            imageView.setLayoutParams(lp);
        }
    }


    /**
     * 得到�?幕的宽度
     */
    public static int getDisplayWidth() {
        try {
            WindowManager wm = (WindowManager) CloudReaderApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
            return wm.getDefaultDisplay().getWidth();
        } catch (Exception e) {
            return 1080;
        }
    }

    @SuppressLint("ResourceType")
    public static void formatBannerHeight(View imageView, View view) {
        float displayWidth = getDisplayWidth();
        float width = (2f / 3 * displayWidth);
        float height = (2f / 3 * (displayWidth / 1.8f));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int) width, (int) height);
        imageView.setLayoutParams(lp);
        imageView.setId(1);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) height);
        lp2.addRule(RelativeLayout.RIGHT_OF, 1);
        view.setLayoutParams(lp2);
    }

    /**
     * 获�?�状�?�?的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getApplicationContext().getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
