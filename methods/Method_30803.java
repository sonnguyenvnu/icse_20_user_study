@TargetApi(Build.VERSION_CODES.LOLLIPOP) public void drawableHotspotChanged(float x,float y){
  mDelegate.superDrawableHotspotChanged(x,y);
  if (mHasFrameworkForeground) {
    return;
  }
  if (mForeground != null) {
    mForeground.setHotspot(x,y);
  }
}
