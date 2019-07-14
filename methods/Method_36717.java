public void storeAspectRatio(){
  if (style != null && !Float.isNaN(style.aspectRatio)) {
    mTmpAspectRatio=style.aspectRatio;
    style.aspectRatio=Float.NaN;
  }
}
