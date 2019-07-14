/** 
 * Get the current first day of the month in month mode, or the first visible day of the currently visible week. For example, in week mode, if the week is July 29th, 2018 to August 4th, 2018, this will return July 29th, 2018. If in month mode and the month is august, then this method will return August 1st, 2018.
 * @return The current month or week shown, will be set to first day of the month in month mode,or the first visible day for a week.
 */
public CalendarDay getCurrentDate(){
  return adapter.getItem(pager.getCurrentItem());
}
