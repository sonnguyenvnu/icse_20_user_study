package com.zlm.hp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlm.hp.lyrics.model.MakeExtraLrcLineInfo;
import com.zlm.hp.lyrics.utils.StringUtils;
import com.zlm.hp.ui.R;
import com.zlm.hp.widget.ListItemRelativeLayout;

import java.util.ArrayList;

/**
 * 制作�?外歌�?适�?器
 * Created by zhangliangming on 2018-03-28.
 */

public class MakeExtraLrcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 歌�?数�?�集�?�
     */
    private ArrayList<MakeExtraLrcLineInfo> mMakeLrcs;

    private Context mContext;

    /**
     * 选中索引
     */
    private int mSelectedIndex = -1;

    /**
     *
     */
    private ItemEvent mItemEvent;

    public MakeExtraLrcAdapter(Context context, ArrayList<MakeExtraLrcLineInfo> makeLrcs) {
        this.mContext = context;
        this.mMakeLrcs = makeLrcs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_lvitem_make_extralrc, null, false);
        MakeExtraLrcViewHolder makeExtraLrcViewHolder = new MakeExtraLrcViewHolder(view);
        return makeExtraLrcViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MakeExtraLrcViewHolder && position < mMakeLrcs.size()) {
            MakeExtraLrcLineInfo makeExtraLrcLineInfo = mMakeLrcs.get(position);
            reshViewHolder((MakeExtraLrcViewHolder) holder, position, makeExtraLrcLineInfo);
        }
    }

    /**
     * @param viewHolder
     * @param position
     * @param makeLrcLineInfo
     */
    private void reshViewHolder(final MakeExtraLrcViewHolder viewHolder, final int position, final MakeExtraLrcLineInfo makeLrcLineInfo) {
        viewHolder.getIndexTv().setText(String.format("%0" + (mMakeLrcs.size() + "").length() + "d", (position + 1)));

        //设置内容
        viewHolder.getLineLyricsYv().setText(makeLrcLineInfo.getLyricsLineInfo().getLineLyrics());
        if (makeLrcLineInfo.getExtraLineLyrics() != null) {
            viewHolder.getExtraLineLyricsYv().setText(makeLrcLineInfo.getExtraLineLyrics());
        } else {
            viewHolder.getExtraLineLyricsYv().setText("");
        }

        String extraLineLyrics = makeLrcLineInfo.getExtraLineLyrics();

        //该行歌�?已�?录制完�?
        if (StringUtils.isNotBlank(extraLineLyrics)) {
            viewHolder.getIndexTv().setTextColor(Color.BLUE);
            viewHolder.getLineLyricsYv().setTextColor(Color.BLUE);
            viewHolder.getExtraLineLyricsYv().setTextColor(Color.BLUE);

        } else {
            viewHolder.getIndexTv().setTextColor(Color.BLACK);
            viewHolder.getLineLyricsYv().setTextColor(Color.BLACK);
            viewHolder.getExtraLineLyricsYv().setTextColor(Color.BLACK);
        }

        //item点击
        viewHolder.getItemBG().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedIndex = position;
                if (mItemEvent != null) {
                    mItemEvent.itemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMakeLrcs.size();
    }

    public void setItemEvent(ItemEvent mItemEvent) {
        this.mItemEvent = mItemEvent;
    }

    /***
     * 获�?�上一行歌�?
     * @return
     */
    public int getPreIndex() {
        mSelectedIndex--;
        if (mSelectedIndex < 0) {
            mSelectedIndex = 0;
        }
        return mSelectedIndex;
    }

    /**
     * 获�?�下一行歌�?
     *
     * @return
     */
    public int getNextIndex() {
        mSelectedIndex++;
        if (mSelectedIndex >= mMakeLrcs.size()) {
            mSelectedIndex = mMakeLrcs.size() - 1;
        }
        return mSelectedIndex;
    }

    /**
     * 更新
     */
    public void saveAndUpdate() {
        notifyItemChanged(mSelectedIndex);
    }

    public void reset(){
        mSelectedIndex = -1;
        notifyDataSetChanged();
    }

    class MakeExtraLrcViewHolder extends RecyclerView.ViewHolder {

        private View view;
        /**
         * 索引
         */
        private TextView indexTv;

        /**
         * 原歌�?
         */
        private TextView lineLyricsYv;

        /**
         * �?外歌�?
         */
        private TextView extraLineLyricsYv;

        private ListItemRelativeLayout itemBG;


        public MakeExtraLrcViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        public ListItemRelativeLayout getItemBG() {
            if (itemBG == null) {
                itemBG = view.findViewById(R.id.itemBG);
            }
            return itemBG;
        }

        public TextView getIndexTv() {
            if (indexTv == null) {
                indexTv = view.findViewById(R.id.index);
            }
            return indexTv;
        }

        public TextView getLineLyricsYv() {
            if (lineLyricsYv == null) {
                lineLyricsYv = view.findViewById(R.id.lineLyrics);
            }
            return lineLyricsYv;
        }

        public TextView getExtraLineLyricsYv() {
            if (extraLineLyricsYv == null) {
                extraLineLyricsYv = view.findViewById(R.id.extraLineLyrics);
            }
            return extraLineLyricsYv;
        }
    }

    public interface ItemEvent {
        void itemClick(int index);
    }
}
