private boolean isInVisibleRange(VisibilityOutput visibilityOutput,Rect bounds,Rect visibleBounds){
  float heightRatio=visibilityOutput.getVisibleHeightRatio();
  float widthRatio=visibilityOutput.getVisibleWidthRatio();
  if (heightRatio == 0 && widthRatio == 0) {
    return true;
  }
  return isInRatioRange(heightRatio,bounds.height(),visibleBounds.height()) && isInRatioRange(widthRatio,bounds.width(),visibleBounds.width());
}
