/** 
 * This moves to the specified time in the view. If the time is not already in range it will move the list so that the first of the month containing the time is at the top of the view. If the new time is already in view the list will not be scrolled unless forceScroll is true. This time may optionally be highlighted as selected as well.
 * @param time The time to move to
 * @param animate Whether to scroll to the given time or just redraw at thenew location
 * @param setSelected Whether to set the given time as selected
 * @param forceScroll Whether to recenter even if the time is alreadyvisible
 * @return Whether or not the view animated to the new location
 */
public boolean goTo(CalendarDay day,boolean animate,boolean setSelected,boolean forceScroll){
  if (setSelected) {
    mSelectedDay.set(day);
  }
  mTempDay.set(day);
  final int position=(day.year - mController.getMinYear()) * MonthAdapter.MONTHS_IN_YEAR + day.month;
  View child;
  int i=0;
  int top=0;
  do {
    child=getChildAt(i++);
    if (child == null) {
      break;
    }
    top=child.getTop();
    if (Log.isLoggable(TAG,Log.DEBUG)) {
      Log.d(TAG,"child at " + (i - 1) + " has top " + top);
    }
  }
 while (top < 0);
  int selectedPosition;
  if (child != null) {
    selectedPosition=getPositionForView(child);
  }
 else {
    selectedPosition=0;
  }
  if (setSelected) {
    mAdapter.setSelectedDay(mSelectedDay);
  }
  if (Log.isLoggable(TAG,Log.DEBUG)) {
    Log.d(TAG,"GoTo position " + position);
  }
  if (position != selectedPosition || forceScroll) {
    setMonthDisplayed(mTempDay);
    mPreviousScrollState=OnScrollListener.SCROLL_STATE_FLING;
    if (animate) {
      smoothScrollToPositionFromTop(position,LIST_TOP_OFFSET,GOTO_SCROLL_DURATION);
      return true;
    }
 else {
      postSetSelection(position);
    }
  }
 else   if (setSelected) {
    setMonthDisplayed(mSelectedDay);
  }
  return false;
}
