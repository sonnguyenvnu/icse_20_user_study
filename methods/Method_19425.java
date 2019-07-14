private void trackLongClickBoundaryOnMove(MotionEvent event){
  final Rect bounds=getBounds();
  if (!isWithinBounds(bounds,event)) {
    resetLongClick();
    return;
  }
  final ClickableSpan clickableSpan=getClickableSpanInCoords((int)event.getX() - bounds.left,(int)event.getY() - bounds.top);
  if (mLongClickRunnable.longClickableSpan != clickableSpan) {
    resetLongClick();
  }
}
