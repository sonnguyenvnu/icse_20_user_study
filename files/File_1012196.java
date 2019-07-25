package com.xuexiang.xuidemo.adapter.base;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;

/**
 *
 *
 * @author xuexiang
 * @since 2019/4/6 下�?�3:45
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getView(int viewId) {
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }

    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }

    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }

    /**
     * 寻找控件
     *
     * @param id
     * @return
     */
    public View findView(@IdRes int id) {
        return id == 0 ? itemView : findViewById(id);
    }

    /**
     * 设置文字
     *
     * @param id
     * @param sequence
     * @return
     */
    public RecyclerViewHolder text(int id, CharSequence sequence) {
        View view = findView(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(sequence);
        }
        return this;
    }

    /**
     * 设置文字
     *
     * @param id
     * @param stringRes
     * @return
     */
    public RecyclerViewHolder text(@IdRes int id, @StringRes int stringRes) {
        View view = findView(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(stringRes);
        }
        return this;
    }

    /**
     * 设置文字的颜色
     *
     * @param id
     * @param colorId
     * @return
     */
    public RecyclerViewHolder textColorId(@IdRes int id, @ColorRes int colorId) {
        View view = findView(id);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(ContextCompat.getColor(view.getContext(), colorId));
        }
        return this;
    }

    /**
     * 设置图片
     *
     * @param id
     * @param imageId
     * @return
     */
    public RecyclerViewHolder image(@IdRes int id, int imageId) {
        View view = findView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(imageId);
        }
        return this;
    }

    /**
     * 设置布局内控件的点击事件�?包�?�索引】
     *
     * @param id
     * @param listener
     * @param position
     * @return
     */
    public RecyclerViewHolder viewClick(@IdRes int id, final SmartViewHolder.OnViewItemClickListener listener, final int position) {
        View view = findView(id);
        if (listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onViewItemClick(v, position);
                }
            });
        }
        return this;
    }

    /**
     * 设置控件的点击监�?�
     *
     * @param id
     * @param listener
     * @return
     */
    public RecyclerViewHolder click(@IdRes int id, final View.OnClickListener listener) {
        View view = findView(id);
        if (listener != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置控件是�?��?�显示
     *
     * @param id
     * @param visibility
     * @return
     */
    public RecyclerViewHolder visible(@IdRes int id, int visibility) {
        View view = findView(id);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置输入框是�?��?�编辑
     *
     * @param id
     * @param enable
     * @return
     */
    public RecyclerViewHolder enable(@IdRes int id, boolean enable) {
        View view = findView(id);
        view.setEnabled(enable);
        if (view instanceof EditText) {
            view.setFocusable(enable);
            view.setFocusableInTouchMode(enable);
        }
        return this;
    }

    /**
     * 这是控件选中状�?
     *
     * @param id
     * @param checked
     * @return
     */
    public RecyclerViewHolder checked(@IdRes int id, boolean checked) {
        View view = findView(id);
        if (view instanceof CompoundButton) {
            ((CheckBox) view).setChecked(checked);
        }
        return this;
    }

    /**
     * 设置控件选择监�?�
     *
     * @param id
     * @param listener
     * @return
     */
    public RecyclerViewHolder checkedListener(@IdRes int id, CompoundButton.OnCheckedChangeListener listener) {
        View view = findView(id);
        if (view instanceof CompoundButton) {
            ((CheckBox) view).setOnCheckedChangeListener(listener);
        }
        return this;
    }

    /**
     * 设置文字�?�化监�?�
     *
     * @param id
     * @param watcher
     * @return
     */
    public RecyclerViewHolder textListener(@IdRes int id, TextWatcher watcher) {
        View view = findView(id);
        if (view instanceof TextView) {
            ((TextView) view).addTextChangedListener(watcher);
        }
        return this;
    }

    /**
     * 设置背景
     *
     * @param viewId
     * @param resId
     * @return
     */
    public RecyclerViewHolder backgroundResId(int viewId, int resId) {
        View view = findView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 清除控件缓存
     */
    public void clearViews() {
        if (mViews != null) {
            mViews.clear();
        }
    }
}
