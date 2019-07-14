private void setupReverse(LinearLayout layoutProgress){
  RelativeLayout.LayoutParams progressParams=(RelativeLayout.LayoutParams)layoutProgress.getLayoutParams();
  removeLayoutParamsRule(progressParams);
  if (isReverse) {
    progressParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      progressParams.addRule(RelativeLayout.ALIGN_PARENT_END);
    }
  }
 else {
    progressParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      progressParams.addRule(RelativeLayout.ALIGN_PARENT_START);
    }
  }
  layoutProgress.setLayoutParams(progressParams);
}
