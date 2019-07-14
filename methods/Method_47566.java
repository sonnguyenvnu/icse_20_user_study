/** 
 * Sets the month displayed at the top of this view based on time. Override to add custom events when the title is changed.
 */
protected void setMonthDisplayed(CalendarDay date){
  mCurrentMonthDisplayed=date.month;
  invalidateViews();
}
