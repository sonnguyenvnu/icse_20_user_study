public boolean canInvertBitmap(){
  return currentMediaDrawable instanceof ExtendedBitmapDrawable || currentImageDrawable instanceof ExtendedBitmapDrawable || currentThumbDrawable instanceof ExtendedBitmapDrawable || staticThumbDrawable instanceof ExtendedBitmapDrawable;
}
