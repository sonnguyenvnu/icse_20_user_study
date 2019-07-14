private void alignTextProgressInsideProgress(){
  RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)tvProgress.getLayoutParams();
  if (isReverse()) {
    params.addRule(RelativeLayout.ALIGN_LEFT,R.id.layout_progress);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      params.addRule(RelativeLayout.ALIGN_START,R.id.layout_progress);
    }
  }
 else {
    params.addRule(RelativeLayout.ALIGN_RIGHT,R.id.layout_progress);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      params.addRule(RelativeLayout.ALIGN_END,R.id.layout_progress);
    }
  }
  tvProgress.setLayoutParams(params);
}
