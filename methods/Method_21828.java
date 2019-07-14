/** 
 * Dispatch date change events to a listener, if set
 * @param day first day of the new month
 */
protected void dispatchOnMonthChanged(final CalendarDay day){
  if (monthListener != null) {
    monthListener.onMonthChanged(MaterialCalendarView.this,day);
  }
}
