@TargetApi(Build.VERSION_CODES.LOLLIPOP) void onActionDown(){
  if (mBackgroundDrawable instanceof StateListDrawable) {
    StateListDrawable drawable=(StateListDrawable)mBackgroundDrawable;
    drawable.setState(new int[]{android.R.attr.state_enabled,android.R.attr.state_pressed});
  }
 else   if (Util.hasLollipop()) {
    RippleDrawable ripple=(RippleDrawable)mBackgroundDrawable;
    ripple.setState(new int[]{android.R.attr.state_enabled,android.R.attr.state_pressed});
    ripple.setHotspot(calculateCenterX(),calculateCenterY());
    ripple.setVisible(true,true);
  }
}
