@Override protected void convert(JViewHolder<ItemAndroidBinding> helper,GankIoDataBean.ResultBean object){
  if (object != null) {
    helper.binding.executePendingBindings();
    if (isAll && "??".equals(object.getType())) {
      helper.binding.ivAllWelfare.setVisibility(View.VISIBLE);
      helper.binding.llWelfareOther.setVisibility(View.GONE);
      GlideUtil.displayEspImage(object.getUrl(),helper.binding.ivAllWelfare,1);
    }
 else {
      helper.binding.ivAllWelfare.setVisibility(View.GONE);
      helper.binding.llWelfareOther.setVisibility(View.VISIBLE);
    }
    if (isAll) {
      helper.binding.tvContentType.setVisibility(View.VISIBLE);
      helper.binding.tvContentType.setText(" · " + object.getType());
    }
 else {
      helper.binding.tvContentType.setVisibility(View.GONE);
    }
    if (object.getImages() != null && object.getImages().size() > 0 && !TextUtils.isEmpty(object.getImages().get(0))) {
      helper.binding.ivAndroidPic.setVisibility(View.VISIBLE);
      GlideUtil.displayGif(object.getImages().get(0),helper.binding.ivAndroidPic);
    }
 else {
      helper.binding.ivAndroidPic.setVisibility(View.GONE);
    }
    helper.binding.llAll.setOnClickListener(v -> WebViewActivity.loadUrl(v.getContext(),object.getUrl(),object.getDesc()));
    helper.binding.setResultsBean(object);
    helper.binding.executePendingBindings();
  }
}
