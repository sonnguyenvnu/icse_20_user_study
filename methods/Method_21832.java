/** 
 * Called by the adapter for cases when changes in state result in dates being unselected
 * @param date date that should be de-selected
 */
protected void onDateUnselected(CalendarDay date){
  dispatchOnDateSelected(date,false);
}
