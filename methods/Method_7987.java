public boolean isInsideBackground(float x,float y){
  return currentBackgroundDrawable != null && x >= backgroundDrawableLeft && x <= backgroundDrawableLeft + backgroundDrawableRight;
}
