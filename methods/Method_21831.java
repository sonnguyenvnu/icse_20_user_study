/** 
 * Call by  {@link CalendarPagerView} to indicate that a day was long clicked and we should handleit
 */
protected void onDateLongClicked(final DayView dayView){
  if (longClickListener != null) {
    longClickListener.onDateLongClick(MaterialCalendarView.this,dayView.getDate());
  }
}
