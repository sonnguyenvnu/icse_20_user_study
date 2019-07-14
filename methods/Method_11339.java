/** 
 * Double tap zoom handler triggered from gesture detector or on touch, depending on whether quick scale is enabled.
 */
private void doubleTapZoom(PointF sCenter,PointF vFocus){
  if (!panEnabled) {
    if (sRequestedCenter != null) {
      sCenter.x=sRequestedCenter.x;
      sCenter.y=sRequestedCenter.y;
    }
 else {
      sCenter.x=sWidth() / 2;
      sCenter.y=sHeight() / 2;
    }
  }
  float doubleTapZoomScale=Math.min(maxScale,RxScaleImageView.this.doubleTapZoomScale);
  boolean zoomIn=scale <= doubleTapZoomScale * 0.9;
  float targetScale=zoomIn ? doubleTapZoomScale : minScale();
  if (doubleTapZoomStyle == ZOOM_FOCUS_CENTER_IMMEDIATE) {
    setScaleAndCenter(targetScale,sCenter);
  }
 else   if (doubleTapZoomStyle == ZOOM_FOCUS_CENTER || !zoomIn || !panEnabled) {
    new AnimationBuilder(targetScale,sCenter).withInterruptible(false).withDuration(doubleTapZoomDuration).withOrigin(ORIGIN_DOUBLE_TAP_ZOOM).start();
  }
 else   if (doubleTapZoomStyle == ZOOM_FOCUS_FIXED) {
    new AnimationBuilder(targetScale,sCenter,vFocus).withInterruptible(false).withDuration(doubleTapZoomDuration).withOrigin(ORIGIN_DOUBLE_TAP_ZOOM).start();
  }
  invalidate();
}
