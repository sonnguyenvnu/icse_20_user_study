private void maybeSetHugeImageController(){
  if (mHugeImageController != null && mZoomableController.getScaleFactor() > HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) {
    setControllersInternal(mHugeImageController,null);
  }
}
