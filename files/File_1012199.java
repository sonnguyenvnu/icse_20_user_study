package com.xuexiang.xuidemo.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.adapter.SmartRecyclerAdapter;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner;
import com.xuexiang.xui.widget.banner.widget.banner.base.BaseBanner;
import com.xuexiang.xuidemo.R;
import com.xuexiang.xutil.tip.ToastUtils;

import java.util.List;

/**
 * 演示如何增加HeadView和FootView
 *
 * @author xuexiang
 * @since 2019/3/31 下�?�11:45
 */
public class RefreshHeadViewAdapter extends SmartRecyclerAdapter<String> {

    private static final int TYPE_BANNER_HEAD = 0;
    private static final int TYPE_COMMON = 1;
    private static final int TYPE_BANNER_FOOT = 2;


    private List<BannerItem> mData;

    public RefreshHeadViewAdapter(List<BannerItem> bannerData) {
        super(android.R.layout.simple_list_item_2);
        mData = bannerData;
    }

    /**
     * 创建�?�目控件
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    protected View generateItemView(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER_HEAD || viewType == TYPE_BANNER_FOOT) {
            return getInflate(parent, R.layout.include_head_view_banner);
        } else {
            return getInflate(parent, android.R.layout.simple_list_item_2);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER_HEAD;
        } else if (position == getCount() - 1) {
            return TYPE_BANNER_FOOT;
        } else {
            return TYPE_COMMON;
        }
    }

    /**
     * 绑定布局控件
     *
     * @param holder
     * @param model
     * @param position
     */
    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
        if (getItemViewType(position) == TYPE_BANNER_HEAD) {
            headBanner = holder.findViewById(R.id.sib_simple_usage);
            headBanner.setSource(mData)
                    .setOnItemClickL(new BaseBanner.OnItemClickL() {
                        @Override
                        public void onItemClick(int position) {
                            ToastUtils.toast("headBanner position--->" + position);
                        }
                    }).startScroll();

        } else if (getItemViewType(position) == TYPE_BANNER_FOOT) {
            footBanner = holder.findViewById(R.id.sib_simple_usage);
            footBanner.setSource(mData)
                    .setOnItemClickL(new BaseBanner.OnItemClickL() {
                        @Override
                        public void onItemClick(int position) {
                            ToastUtils.toast("footBanner position--->" + position);
                        }
                    }).startScroll();
        } else {
            holder.text(android.R.id.text1, ResUtils.getResources().getString(R.string.item_example_number_title, position));
            holder.text(android.R.id.text2, ResUtils.getResources().getString(R.string.item_example_number_abstract, position) + "：" + model);
            holder.textColorId(android.R.id.text2, R.color.xui_config_color_light_blue_gray);
        }
    }

    private SimpleImageBanner headBanner;

    private SimpleImageBanner footBanner;

    /**
     * 资�?释放
     */
    public void recycle() {
        if (headBanner != null) {
            headBanner.recycle();
        }
        if (footBanner != null) {
            footBanner.recycle();
        }
        clear();
    }
}
