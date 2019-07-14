@Override public int getIntrinsicHeight(){
  int height=-1;
  for (int i=0; i < mLayers.length; i++) {
    Drawable drawable=mLayers[i];
    if (drawable != null) {
      height=Math.max(height,drawable.getIntrinsicHeight());
    }
  }
  return height > 0 ? height : -1;
}
