@Override protected void convert(VideoHeaderViewHolder holder,QQVideoTabListBean.IndexBean data){
  holder.setText(R.id.tv_item_video_fragment_video_header_title,data.getDisplay_name());
  holder.bindData(data).setOnItemClickListener(new OnSimpleItemClickListener(){
    @Override public void onItemClick(    int position,    View view){
      if (mOnInnerItemClickListener != null) {
        mOnInnerItemClickListener.onItemClick(holder.getAdapterPosition(),position,data);
      }
    }
  }
);
}
