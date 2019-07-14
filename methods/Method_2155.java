private boolean shouldStartDoubleTapScroll(PointF viewPoint){
  double dist=Math.hypot(viewPoint.x - mDoubleTapViewPoint.x,viewPoint.y - mDoubleTapViewPoint.y);
  return dist > DOUBLE_TAP_SCROLL_THRESHOLD;
}
