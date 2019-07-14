/** 
 * @return The date that has accessibility focus, or {@code null} if no datehas focus
 */
public CalendarDay getAccessibilityFocus(){
  final int day=mTouchHelper.getFocusedVirtualView();
  if (day >= 0) {
    return new CalendarDay(mYear,mMonth,day);
  }
  return null;
}
