private void maybeUpdateDebugOverlay(@Nullable CloseableImage image){
  if (!mDrawDebugOverlay) {
    return;
  }
  if (getControllerOverlay() == null) {
    final DebugControllerOverlayDrawable controllerOverlay=new DebugControllerOverlayDrawable();
    ImageLoadingTimeControllerListener overlayImageLoadListener=new ImageLoadingTimeControllerListener(controllerOverlay);
    mDebugOverlayImageOriginListener=new DebugOverlayImageOriginListener();
    addControllerListener(overlayImageLoadListener);
    setControllerOverlay(controllerOverlay);
  }
  if (mImageOriginListener == null) {
    addImageOriginListener(mDebugOverlayImageOriginListener);
  }
  if (getControllerOverlay() instanceof DebugControllerOverlayDrawable) {
    updateDebugOverlay(image,(DebugControllerOverlayDrawable)getControllerOverlay());
  }
}
