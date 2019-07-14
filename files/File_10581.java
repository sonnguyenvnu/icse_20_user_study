package com.vondear.rxdemo.view;

/**
 * Created by vondear on 16-11-13.
 */

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * 自定义估值器
 * @author vondear
 */
public class RxPointFTypeEvaluator implements TypeEvaluator<PointF> {
    /**
     * �?个估值器对应一个属性动画，�?个属性动画仅对应唯一一个控制点
     */
    PointF control;
    /**
     * 估值器返回值
     */
    PointF mPointF = new PointF();

    public RxPointFTypeEvaluator(PointF control) {
        this.control = control;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return getBezierPoint(startValue, endValue, control, fraction);
    }

    /**
     * 二次�?塞尔曲线公�?
     *
     * @param start   开始的数�?�点
     * @param end     结�?�的数�?�点
     * @param control 控制点
     * @param t       float 0-1
     * @return �?�?�t对应的PointF
     */
    private PointF getBezierPoint(PointF start, PointF end, PointF control, float t) {
        mPointF.x = (1 - t) * (1 - t) * start.x + 2 * t * (1 - t) * control.x + t * t * end.x;
        mPointF.y = (1 - t) * (1 - t) * start.y + 2 * t * (1 - t) * control.y + t * t * end.y;
        return mPointF;
    }
}
