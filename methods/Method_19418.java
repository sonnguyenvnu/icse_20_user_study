private boolean handleTouchForSpans(MotionEvent event,View view){
  final int action=event.getActionMasked();
  if (action == ACTION_CANCEL) {
    clearSelection();
    resetLongClick();
    return false;
  }
  if (action == ACTION_MOVE && !mLongClickActivated && mLongClickRunnable != null) {
    trackLongClickBoundaryOnMove(event);
  }
  final boolean clickActivationAllowed=!mLongClickActivated;
  if (action == ACTION_UP) {
    resetLongClick();
  }
  final Rect bounds=getBounds();
  if (!isWithinBounds(bounds,event)) {
    return false;
  }
  final int x=(int)event.getX() - bounds.left;
  final int y=(int)event.getY() - bounds.top;
  ClickableSpan clickedSpan=getClickableSpanInCoords(x,y);
  if (clickedSpan == null && mClickableSpanExpandedOffset > 0) {
    clickedSpan=getClickableSpanInProximityToClick(x,y,mClickableSpanExpandedOffset);
  }
  if (clickedSpan == null) {
    clearSelection();
    return false;
  }
  if (action == ACTION_UP) {
    clearSelection();
    if (clickActivationAllowed && (mSpanListener == null || !mSpanListener.onClick(clickedSpan,view))) {
      clickedSpan.onClick(view);
    }
  }
 else   if (action == ACTION_DOWN) {
    if (clickedSpan instanceof LongClickableSpan) {
      registerForLongClick((LongClickableSpan)clickedSpan,view);
    }
    setSelection(clickedSpan);
  }
  return true;
}
