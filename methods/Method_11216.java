private void clearTextProgressAlign(){
  RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)tvProgress.getLayoutParams();
  params.addRule(RelativeLayout.ALIGN_LEFT,0);
  params.addRule(RelativeLayout.ALIGN_RIGHT,0);
  params.addRule(RelativeLayout.LEFT_OF,0);
  params.addRule(RelativeLayout.RIGHT_OF,0);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    params.removeRule(RelativeLayout.START_OF);
    params.removeRule(RelativeLayout.END_OF);
    params.removeRule(RelativeLayout.ALIGN_START);
    params.removeRule(RelativeLayout.ALIGN_END);
  }
  tvProgress.setLayoutParams(params);
}
