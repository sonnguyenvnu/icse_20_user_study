public void setBgSelector(){
  StateListDrawable bg=new StateListDrawable();
  setDrawable(gd_background,backgroundColor,strokeColor);
  bg.addState(new int[]{-android.R.attr.state_pressed},gd_background);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    setBackground(bg);
  }
 else {
    setBackgroundDrawable(bg);
  }
}
