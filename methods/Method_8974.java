public boolean swapBackground(Drawable drawable){
  if (currentDrawable != drawable) {
    currentDrawable=drawable;
    return true;
  }
  return false;
}
