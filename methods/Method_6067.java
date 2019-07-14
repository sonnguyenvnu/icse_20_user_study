private void maybeNotifyVideoSizeChanged(){
  if ((currentWidth != Format.NO_VALUE || currentHeight != Format.NO_VALUE) && (reportedWidth != currentWidth || reportedHeight != currentHeight || reportedUnappliedRotationDegrees != currentUnappliedRotationDegrees || reportedPixelWidthHeightRatio != currentPixelWidthHeightRatio)) {
    eventDispatcher.videoSizeChanged(currentWidth,currentHeight,currentUnappliedRotationDegrees,currentPixelWidthHeightRatio);
    reportedWidth=currentWidth;
    reportedHeight=currentHeight;
    reportedUnappliedRotationDegrees=currentUnappliedRotationDegrees;
    reportedPixelWidthHeightRatio=currentPixelWidthHeightRatio;
  }
}
