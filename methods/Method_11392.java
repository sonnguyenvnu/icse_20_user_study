private int computeDesiredWidth(){
  final int contentWidth=(int)(animateMeasurementChange ? columnManager.getCurrentWidth() : columnManager.getMinimumRequiredWidth());
  return contentWidth + getPaddingLeft() + getPaddingRight();
}
