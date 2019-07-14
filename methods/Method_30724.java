@Override public void setAdjustViewBounds(boolean adjustViewBounds){
  ScaleType scaleType=getScaleType();
  super.setAdjustViewBounds(adjustViewBounds);
  setScaleType(scaleType);
}
