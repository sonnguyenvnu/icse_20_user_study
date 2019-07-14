@Override public void initData(){
  super.initData();
  if (tvBaseTitle != null) {
    String title=getIntent().getStringExtra(INTENT_TITLE);
    if (StringUtil.isNotEmpty(title,true) == false) {
      title=getTitleName();
    }
    tvBaseTitle.setVisibility(StringUtil.isNotEmpty(title,true) ? View.VISIBLE : View.GONE);
    tvBaseTitle.setText(StringUtil.getTrimedString(title));
  }
  if (tvBaseViewBottomWindowReturn != null && StringUtil.isNotEmpty(getReturnName(),true)) {
    tvBaseViewBottomWindowReturn.setText(StringUtil.getCurrentString());
  }
  if (tvBaseViewBottomWindowForward != null && StringUtil.isNotEmpty(getForwardName(),true)) {
    tvBaseViewBottomWindowForward.setText(StringUtil.getCurrentString());
  }
  llBaseViewBottomWindowContainer.removeAllViews();
  if (containerView == null) {
    containerView=createView();
    llBaseViewBottomWindowContainer.addView(containerView.createView(inflater));
  }
  containerView.bindView(null);
}
