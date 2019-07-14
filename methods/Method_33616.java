private void initData(){
  String type=SPUtils.getString(GANK_CALA,"??");
  if ("??".equals(type)) {
    mType="all";
  }
 else   if ("IOS".equals(type)) {
    mType="iOS";
  }
 else {
    mType=type;
  }
  viewModel.setType(mType);
}
