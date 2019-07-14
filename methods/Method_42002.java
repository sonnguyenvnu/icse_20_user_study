@Override public void onVisibilityChange(int visibility){
  if (visibility == View.GONE)   hideControls();
 else   if (visibility == View.VISIBLE)   showControls();
}
