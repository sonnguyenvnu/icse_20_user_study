/** 
 * Attempts to restore accessibility focus to the specified date.
 * @param day The date which should receive focus
 * @return {@code false} if the date is not valid for this month view, or{@code true} if the date received focus
 */
public boolean restoreAccessibilityFocus(CalendarDay day){
  if ((day.year != mYear) || (day.month != mMonth) || (day.day > mNumCells)) {
    return false;
  }
  mTouchHelper.setFocusedVirtualView(day.day);
  return true;
}
