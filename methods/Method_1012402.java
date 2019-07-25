private void change(BaseWrappedViewHolder holder,boolean isSelected){
  if (holder != null) {
    if (isSelected) {
      holder.getView(R.id.tv_item_activity_qq_video_detail_tv_title).setSelected(true);
    }
 else {
      holder.getView(R.id.tv_item_activity_qq_video_detail_tv_title).setSelected(false);
    }
  }
}
