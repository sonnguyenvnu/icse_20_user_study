public void onScale(float scale,float x,float y){
  if (animating) {
    return;
  }
  float newScale=state.getScale() * scale;
  if (newScale > MAX_SCALE)   scale=MAX_SCALE / state.getScale();
  float statusBarHeight=(Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
  float pivotX=(x - imageView.getWidth() / 2) / areaView.getCropWidth() * state.getOrientedWidth();
  float pivotY=(y - (imageView.getHeight() - bottomPadding - statusBarHeight) / 2) / areaView.getCropHeight() * state.getOrientedHeight();
  state.scale(scale,pivotX,pivotY);
  updateMatrix();
}
