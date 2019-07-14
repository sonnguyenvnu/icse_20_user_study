@TargetApi(Build.VERSION_CODES.LOLLIPOP) void onActionDown(){
  if (mUsingStyle) {
    mBackgroundDrawable=getBackground();
  }
  if (mBackgroundDrawable instanceof StateListDrawable) {
    StateListDrawable drawable=(StateListDrawable)mBackgroundDrawable;
    drawable.setState(new int[]{android.R.attr.state_pressed});
  }
 else   if (Util.hasLollipop() && mBackgroundDrawable instanceof RippleDrawable) {
    RippleDrawable ripple=(RippleDrawable)mBackgroundDrawable;
    ripple.setState(new int[]{android.R.attr.state_enabled,android.R.attr.state_pressed});
    ripple.setHotspot(getMeasuredWidth() / 2,getMeasuredHeight() / 2);
    ripple.setVisible(true,true);
  }
}
