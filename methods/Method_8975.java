public boolean swapMiniBackground(Drawable drawable){
  if (currentMiniDrawable != drawable) {
    currentMiniDrawable=drawable;
    drawMiniProgress=previousMiniDrawable != null || currentMiniDrawable != null;
    return true;
  }
  return false;
}
