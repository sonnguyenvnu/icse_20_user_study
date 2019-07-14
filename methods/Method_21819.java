/** 
 * Set the calendar to a specific month or week based on a date. In month mode, the calendar will be set to the corresponding month. In week mode, the calendar will be set to the corresponding week.
 * @param day a CalendarDay to focus the calendar on. Null will do nothing
 * @param useSmoothScroll use smooth scroll when changing months.
 */
public void setCurrentDate(@Nullable CalendarDay day,boolean useSmoothScroll){
  if (day == null) {
    return;
  }
  int index=adapter.getIndexForDay(day);
  pager.setCurrentItem(index,useSmoothScroll);
  updateUi();
}
