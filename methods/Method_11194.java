private void removeLayoutParamsRule(RelativeLayout.LayoutParams layoutParams){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
    layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
    layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
  }
 else {
    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,0);
    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,0);
  }
}
