package com.xuexiang.xuidemo.adapter.base;

import androidx.annotation.LayoutRes;
import android.view.View;

import com.scwang.smartrefresh.layout.adapter.SmartRecyclerAdapter;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import me.samlss.broccoli.Broccoli;

/**
 * 使用Broccoli�?��?的基础适�?器
 *
 * @author XUE
 * @since 2019/4/8 16:33
 */
public abstract class BroccoliRecyclerAdapter<T> extends SmartRecyclerAdapter<T> {
    /**
     * 是�?�已�?加载�?功
     */
    private boolean mHasLoad = false;
    private Map<View, Broccoli> mBroccoliMap = new HashMap<>();

    public BroccoliRecyclerAdapter(Collection<T> collection, @LayoutRes int layoutId) {
        super(collection, layoutId);
    }

    /**
     * 绑定布局控件
     *
     * @param holder
     * @param model
     * @param position
     */
    @Override
    protected void onBindViewHolder(SmartViewHolder holder, T model, int position) {
        Broccoli broccoli = mBroccoliMap.get(holder.itemView);
        if (broccoli == null) {
            broccoli = new Broccoli();
            mBroccoliMap.put(holder.itemView, broccoli);
        }
        if (mHasLoad) {
            broccoli.removeAllPlaceholders();

            onBindData(holder, model, position);
        } else {
            onBindBroccoli(holder, broccoli);
            broccoli.show();
        }
    }

    /**
     * 绑定控件
     *
     * @param holder
     * @param model
     * @param position
     */
    protected abstract void onBindData(SmartViewHolder holder, T model, int position);

    /**
     * 绑定�?��?控件
     *
     * @param broccoli
     */
    protected abstract void onBindBroccoli(SmartViewHolder holder, Broccoli broccoli);

    @Override
    public SmartRecyclerAdapter<T> refresh(Collection<T> collection) {
        mHasLoad = true;
        return super.refresh(collection);
    }

    /**
     * 资�?释放，防止内存泄�?
     */
    public void recycle() {
        for (Broccoli broccoli : mBroccoliMap.values()) {
            broccoli.removeAllPlaceholders();
        }
        mBroccoliMap.clear();
        clear();
    }


}
