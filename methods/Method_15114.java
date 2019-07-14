@Override public void initData(){
  if (tvBaseTabTitle != null) {
    tvBaseTabTitle.setVisibility(StringUtil.isNotEmpty(getTitleName(),true) ? View.VISIBLE : View.GONE);
    tvBaseTabTitle.setText(StringUtil.getTrimedString(getTitleName()));
  }
  String returnName=getReturnName();
  if (returnName == null) {
    if (ivBaseTabReturn != null) {
      ivBaseTabReturn.setVisibility(View.GONE);
    }
    if (tvBaseTabReturn != null) {
      tvBaseTabReturn.setVisibility(View.GONE);
    }
  }
 else {
    boolean isReturnButtonHasName=StringUtil.isNotEmpty(returnName,true);
    if (ivBaseTabReturn != null) {
      ivBaseTabReturn.setVisibility(isReturnButtonHasName ? View.GONE : View.VISIBLE);
    }
    if (tvBaseTabReturn != null) {
      tvBaseTabReturn.setVisibility(isReturnButtonHasName ? View.VISIBLE : View.GONE);
      tvBaseTabReturn.setText(StringUtil.getTrimedString(returnName));
    }
  }
  String forwardName=getForwardName();
  if (StringUtil.isNotEmpty(forwardName,true)) {
    tvBaseTabForward=addTopRightButton(newTopRightTextView(context,StringUtil.getTrimedString(forwardName)));
  }
  if (llBaseTabTopRightButtonContainer != null && topRightButtonList != null && topRightButtonList.size() > 0) {
    llBaseTabTopRightButtonContainer.removeAllViews();
    for (    View btn : topRightButtonList) {
      llBaseTabTopRightButtonContainer.addView(btn);
    }
  }
  topTabView=new TopTabView(context,getResources());
  llBaseTabTabContainer.removeAllViews();
  llBaseTabTabContainer.addView(topTabView.createView(getLayoutInflater()));
  topTabView.setCurrentPosition(currentPosition);
  topTabView.bindView(getTabNames());
  selectFragment(currentPosition);
}
