private void changeContent(TextView textView,String content){
  if ("??".equals(content)) {
    textView.setText("??");
    mType="all";
  }
 else   if ("IOS".equals(content)) {
    textView.setText("IOS");
    mType="iOS";
  }
 else {
    textView.setText(content);
    mType=content;
  }
  bindingView.xrvCustom.reset();
  viewModel.setType(mType);
  viewModel.setPage(1);
  SPUtils.putString(GANK_CALA,content);
  showLoading();
  loadCustomData();
}
