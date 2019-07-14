public void restoreAspectRatio(){
  if (style != null && !Float.isNaN(mTmpAspectRatio)) {
    style.aspectRatio=mTmpAspectRatio;
  }
}
