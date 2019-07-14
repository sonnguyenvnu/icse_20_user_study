private int getZoomForPercent(int zoomPercent){
  List<Integer> zoomRatios=mCameraParameters.getZoomRatios();
  int lowerIndex=-1;
  int upperIndex=-1;
  for (int i=0; i < zoomRatios.size(); i++) {
    if (zoomRatios.get(i) < zoomPercent) {
      lowerIndex=i;
    }
 else     if (zoomRatios.get(i) > zoomPercent) {
      upperIndex=i;
      break;
    }
  }
  if (lowerIndex < 0) {
    return 0;
  }
  if (lowerIndex + 1 == upperIndex) {
    return lowerIndex;
  }
  if (upperIndex >= 0) {
    return upperIndex;
  }
  return zoomRatios.size() - 1;
}
