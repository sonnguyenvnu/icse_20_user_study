@Override protected void convert(ViewHolder holder,Map<String,String> item,int position){
  holder.mTvTitle.setText(item.get(KEY_TITLE));
  if (!StringUtils.isEmpty(item.get(KEY_SUB_TITLE))) {
    holder.mTvSubTitle.setText(item.get(KEY_SUB_TITLE));
    holder.mTvSubTitle.setVisibility(View.VISIBLE);
  }
 else {
    holder.mTvSubTitle.setVisibility(View.GONE);
  }
}
