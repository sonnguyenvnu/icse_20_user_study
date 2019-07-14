@Override public void onScale(float scaleFactor,float focusX,float focusY){
  if ((getScale() < mMaxScale || scaleFactor < 1f) && (getScale() > mMinScale || scaleFactor > 1f)) {
    if (null != mScaleChangeListener) {
      mScaleChangeListener.onScaleChange(scaleFactor,focusX,focusY);
    }
    mSuppMatrix.postScale(scaleFactor,scaleFactor,focusX,focusY);
    checkAndDisplayMatrix();
  }
}
