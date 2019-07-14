@TargetApi(Build.VERSION_CODES.LOLLIPOP) void onActionUp(){
  if (mBackgroundDrawable instanceof StateListDrawable) {
    StateListDrawable drawable=(StateListDrawable)mBackgroundDrawable;
    drawable.setState(new int[]{android.R.attr.state_enabled});
  }
 else   if (Util.hasLollipop()) {
    RippleDrawable ripple=(RippleDrawable)mBackgroundDrawable;
    ripple.setState(new int[]{android.R.attr.state_enabled});
    ripple.setHotspot(calculateCenterX(),calculateCenterY());
    ripple.setVisible(true,true);
  }
}
