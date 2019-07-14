@VisibleForTesting int determineOverlayColor(int imageWidth,int imageHeight,@Nullable ScaleType scaleType){
  int visibleDrawnAreaWidth=getBounds().width();
  int visibleDrawnAreaHeight=getBounds().height();
  if (visibleDrawnAreaWidth <= 0 || visibleDrawnAreaHeight <= 0 || imageWidth <= 0 || imageHeight <= 0) {
    return OVERLAY_COLOR_IMAGE_NOT_OK;
  }
  if (scaleType != null) {
    mRect.left=mRect.top=0;
    mRect.right=visibleDrawnAreaWidth;
    mRect.bottom=visibleDrawnAreaHeight;
    mMatrix.reset();
    scaleType.getTransform(mMatrix,mRect,imageWidth,imageHeight,0f,0f);
    mRectF.left=mRectF.top=0;
    mRectF.right=imageWidth;
    mRectF.bottom=imageHeight;
    mMatrix.mapRect(mRectF);
    final int drawnAreaWidth=(int)mRectF.width();
    final int drawnAreaHeight=(int)mRectF.height();
    visibleDrawnAreaWidth=Math.min(visibleDrawnAreaWidth,drawnAreaWidth);
    visibleDrawnAreaHeight=Math.min(visibleDrawnAreaHeight,drawnAreaHeight);
  }
  float scaledImageWidthThresholdOk=visibleDrawnAreaWidth * IMAGE_SIZE_THRESHOLD_OK;
  float scaledImageWidthThresholdNotOk=visibleDrawnAreaWidth * IMAGE_SIZE_THRESHOLD_NOT_OK;
  float scaledImageHeightThresholdOk=visibleDrawnAreaHeight * IMAGE_SIZE_THRESHOLD_OK;
  float scaledImageHeightThresholdNotOk=visibleDrawnAreaHeight * IMAGE_SIZE_THRESHOLD_NOT_OK;
  int absWidthDifference=Math.abs(imageWidth - visibleDrawnAreaWidth);
  int absHeightDifference=Math.abs(imageHeight - visibleDrawnAreaHeight);
  if (absWidthDifference < scaledImageWidthThresholdOk && absHeightDifference < scaledImageHeightThresholdOk) {
    return OVERLAY_COLOR_IMAGE_OK;
  }
 else   if (absWidthDifference < scaledImageWidthThresholdNotOk && absHeightDifference < scaledImageHeightThresholdNotOk) {
    return OVERLAY_COLOR_IMAGE_ALMOST_OK;
  }
  return OVERLAY_COLOR_IMAGE_NOT_OK;
}
