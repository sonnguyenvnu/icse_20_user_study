void updateMeasureSpecs(int totalSpace){
  mSizePerSpan=totalSpace / mSpanCount;
  mFullSizeSpec=View.MeasureSpec.makeMeasureSpec(totalSpace,mSecondaryOrientation.getMode());
}
