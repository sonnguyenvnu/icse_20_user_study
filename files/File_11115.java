package com.vondear.rxui.view.popupwindows;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vondear.rxtool.RxDeviceTool;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.model.ActionItem;
import com.vondear.rxui.R;

import java.util.ArrayList;

/***
 * @author vondear
 * 功能�??述：标题按钮上的弹窗（继承自PopupWindow）
 */
public class RxPopupSingleView extends PopupWindow {
    // 列表弹窗的间隔
    protected final int LIST_PADDING = 10;
    // �??标的�?置（x�?y）
    private final int[] mLocation = new int[2];
    private Context mContext;
    // 实例化一个矩形
    private Rect mRect = new Rect();
    // �?幕的宽度和高度
    private int mScreenWidth, mScreenHeight;

    // 判断是�?�需�?添加或更新列表�?类项
    private boolean mIsDirty;

    // �?置�?在中心
    private int popupGravity = Gravity.NO_GRAVITY;

    // 弹窗�?类项选中时的监�?�
    private OnItemOnClickListener mItemOnClickListener;

    // 定义列表对象
    private ListView mListView;

    // 定义弹窗�?类项列表
    private ArrayList<ActionItem> mActionItems = new ArrayList<ActionItem>();
    private int colorItemText = 0;

    public RxPopupSingleView(Context context) {
        // 设置布局的�?�数
        this(context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    public RxPopupSingleView(Context context, int width, int height) {
        this.mContext = context;

        // 设置�?�以获得焦点
        setFocusable(true);
        // 设置弹窗内�?�点击
        setTouchable(true);
        // 设置弹窗外�?�点击
        setOutsideTouchable(true);

        // 获得�?幕的宽度和高度
        mScreenWidth = RxDeviceTool.getScreenWidth(mContext);
        mScreenHeight = RxDeviceTool.getScreenHeight(mContext);

        // 设置弹窗的宽度和高度
        setWidth(width);
        setHeight(height);

        setBackgroundDrawable(new BitmapDrawable());

        // 设置弹窗的布局界�?�
        setContentView(LayoutInflater.from(mContext).inflate(
                R.layout.popupwindow_layout, null));

        initUI();
    }

    public RxPopupSingleView(Context context, int width, int height, int layout) {
        this.mContext = context;

        // 设置�?�以获得焦点
        setFocusable(true);
        // 设置弹窗内�?�点击
        setTouchable(true);
        // 设置弹窗外�?�点击
        setOutsideTouchable(true);

        // 获得�?幕的宽度和高度
        mScreenWidth = RxDeviceTool.getScreenWidth(mContext);
        mScreenHeight = RxDeviceTool.getScreenHeight(mContext);

        // 设置弹窗的宽度和高度
        setWidth(width);
        setHeight(height);

        setBackgroundDrawable(new BitmapDrawable());

        // 设置弹窗的布局界�?�
        setContentView(LayoutInflater.from(mContext).inflate(
                layout, null));

        initUI();
    }

    /**
     * �?始化弹窗列表
     */
    private void initUI() {
        mListView = getContentView().findViewById(R.id.title_list);

        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                                    long arg3) {
                // 点击�?类项�?�，弹窗消失
                dismiss();

                if (mItemOnClickListener != null) {
                    mItemOnClickListener.onItemClick(mActionItems.get(index),
                            index);
                }
            }
        });
    }

    /**
     * 显示弹窗列表界�?�
     */
    public void show(View view) {
        // 获得点击�?幕的�?置�??标
        view.getLocationOnScreen(mLocation);

        // 设置矩形的大�?
        mRect.set(mLocation[0], mLocation[1], mLocation[0] + view.getWidth(),
                mLocation[1] + view.getHeight());

        // 判断是�?�需�?添加或更新列表�?类项
        if (mIsDirty) {
            populateActions();
        }

        // 显示弹窗的�?置
        showAtLocation(view, popupGravity, mScreenWidth - LIST_PADDING - (getWidth() / 2), mRect.bottom + RxImageTool.dp2px(7.5f));
    }

    /**
     * 显示弹窗列表界�?�
     */
    public void show(View view, int dex) {
        // 获得点击�?幕的�?置�??标
        view.getLocationOnScreen(mLocation);

        // 设置矩形的大�?
        mRect.set(mLocation[0], mLocation[1], mLocation[0] + view.getWidth(),
                mLocation[1] + view.getHeight());

        // 判断是�?�需�?添加或更新列表�?类项
        if (mIsDirty) {
            populateActions();
        }

        // 显示弹窗的�?置
        showAtLocation(view, popupGravity, mLocation[0], mRect.bottom + dex);
    }

    public void setColorItemText(int colorItemText) {
        this.colorItemText = colorItemText;
    }

    /**
     * 设置弹窗列表�?项
     */
    private void populateActions() {
        mIsDirty = false;

        // 设置列表的适�?器
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv_itpop = null;
                ImageView iv_itpop = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_popup, null);
                }
                tv_itpop = convertView.findViewById(R.id.tv_itpop);
                iv_itpop = convertView.findViewById(R.id.iv_itpop);
                if (colorItemText == 0) {
                    colorItemText = mContext.getResources().getColor(android.R.color.white);
                }
                tv_itpop.setTextColor(colorItemText);
                tv_itpop.setTextSize(14);
                // 设置文本居中
                tv_itpop.setGravity(Gravity.CENTER);
                // 设置文本域的范围
                tv_itpop.setPadding(0, 10, 0, 10);
                // 设置文本在一行内显示（�?�?�行）
                tv_itpop.setSingleLine(true);

                ActionItem item = mActionItems.get(position);

                // 设置文本文字
                tv_itpop.setText(item.mTitle);
                if (item.mResourcesId == 0) {
                    iv_itpop.setVisibility(View.GONE);
                } else {
                    iv_itpop.setVisibility(View.VISIBLE);
                    iv_itpop.setImageResource(item.mResourcesId);
                }

                return convertView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return mActionItems.get(position);
            }

            @Override
            public int getCount() {
                return mActionItems.size();
            }
        });
    }

    /**
     * 添加�?类项
     */
    public void addAction(ActionItem action) {
        if (action != null) {
            mActionItems.add(action);
            mIsDirty = true;
        }
    }

    /**
     * 清除�?类项
     */
    public void cleanAction() {
        if (mActionItems.isEmpty()) {
            mActionItems.clear();
            mIsDirty = true;
        }
    }

    /**
     * 根�?��?置得到�?类项
     */
    public ActionItem getAction(int position) {
        if (position < 0 || position > mActionItems.size()) {
            return null;
        }
        return mActionItems.get(position);
    }

    /**
     * 设置监�?�事件
     */
    public void setItemOnClickListener(
            OnItemOnClickListener onItemOnClickListener) {
        this.mItemOnClickListener = onItemOnClickListener;
    }

    /**
     * @author yangyu 功能�??述：弹窗�?类项按钮监�?�事件
     */
    public interface OnItemOnClickListener {
        void onItemClick(ActionItem item, int position);
    }
}
