/** 
 * Maintains the same hour/min/sec but moves the day to the tapped day.
 * @param day The day that was tapped
 */
protected void onDayTapped(CalendarDay day){
  mController.tryVibrate();
  mController.onDayOfMonthSelected(day.year,day.month,day.day);
  setSelectedDay(day);
}
