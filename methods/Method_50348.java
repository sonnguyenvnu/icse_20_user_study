@Override public void setMinimumScale(float minimumScale){
  checkZoomLevels(minimumScale,mMidScale,mMaxScale);
  mMinScale=minimumScale;
}
