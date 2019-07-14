@Override public boolean onSingleTapUp(MotionEvent e){
  if (!isEditable)   return false;
  performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
  float x, y;
  try {
    int pointerId=e.getPointerId(0);
    x=e.getX(pointerId);
    y=e.getY(pointerId);
  }
 catch (  RuntimeException ex) {
    return false;
  }
  final Timestamp timestamp=positionToTimestamp(x,y);
  if (timestamp == null)   return false;
  Timestamp today=DateUtils.getToday();
  int offset=timestamp.daysUntil(today);
  if (offset < checkmarks.length) {
    boolean isChecked=checkmarks[offset] == CHECKED_EXPLICITLY;
    checkmarks[offset]=(isChecked ? UNCHECKED : CHECKED_EXPLICITLY);
  }
  controller.onToggleCheckmark(timestamp);
  postInvalidate();
  return true;
}
