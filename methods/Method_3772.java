private void updateMeasurements(){
  int totalSpace;
  if (getOrientation() == VERTICAL) {
    totalSpace=getWidth() - getPaddingRight() - getPaddingLeft();
  }
 else {
    totalSpace=getHeight() - getPaddingBottom() - getPaddingTop();
  }
  calculateItemBorders(totalSpace);
}
