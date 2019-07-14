@TargetApi(Build.VERSION_CODES.LOLLIPOP) void onActionUp(){
  if (mUsingStyle) {
    mBackgroundDrawable=getBackground();
  }
  if (mBackgroundDrawable instanceof StateListDrawable) {
    StateListDrawable drawable=(StateListDrawable)mBackgroundDrawable;
    drawable.setState(new int[]{});
  }
 else   if (Util.hasLollipop() && mBackgroundDrawable instanceof RippleDrawable) {
    RippleDrawable ripple=(RippleDrawable)mBackgroundDrawable;
    ripple.setState(new int[]{});
    ripple.setHotspot(getMeasuredWidth() / 2,getMeasuredHeight() / 2);
    ripple.setVisible(true,true);
  }
}
