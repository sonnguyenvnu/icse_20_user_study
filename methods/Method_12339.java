private void applyGifViewAttributes(){
  if (viewAttributes.mLoopCount < 0) {
    return;
  }
  for (  final Drawable drawable : getCompoundDrawables()) {
    GifViewUtils.applyLoopCount(viewAttributes.mLoopCount,drawable);
  }
  for (  final Drawable drawable : getCompoundDrawablesRelative()) {
    GifViewUtils.applyLoopCount(viewAttributes.mLoopCount,drawable);
  }
  GifViewUtils.applyLoopCount(viewAttributes.mLoopCount,getBackground());
}
