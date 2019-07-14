public boolean isReady(){
  return !detector.isScaling() && !detector.isDragging() && !areaView.isDragging();
}
