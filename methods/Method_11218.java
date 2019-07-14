private void alignTextProgressOutsideProgress(){
  RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)tvProgress.getLayoutParams();
  if (isReverse()) {
    params.addRule(RelativeLayout.LEFT_OF,R.id.layout_progress);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      params.addRule(RelativeLayout.START_OF,R.id.layout_progress);
    }
  }
 else {
    params.addRule(RelativeLayout.RIGHT_OF,R.id.layout_progress);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      params.addRule(RelativeLayout.END_OF,R.id.layout_progress);
    }
  }
  tvProgress.setLayoutParams(params);
}
