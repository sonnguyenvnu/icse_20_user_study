private void maybeRenotifyVideoSizeChanged(){
  if (reportedWidth != Format.NO_VALUE || reportedHeight != Format.NO_VALUE) {
    eventDispatcher.videoSizeChanged(reportedWidth,reportedHeight,reportedUnappliedRotationDegrees,reportedPixelWidthHeightRatio);
  }
}
