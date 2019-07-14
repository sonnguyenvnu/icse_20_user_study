/** 
 * Get the day of week from a date. 0 for SUN. 1 for MON. . . . 6 for SAT.
 * @param year The year of the date.
 * @param month The month of the date.
 * @param day The day of month of the date.
 * @return Integer to determine the day of week.
 */
@SuppressLint("WrongConstant") public static int getWeekDayFromDate(int year,int month,int day){
  Calendar calendar=Calendar.getInstance();
  calendar.set(year,month - 1,day);
  calendar.add(Calendar.SECOND,0);
  calendar.setFirstDayOfWeek(Calendar.SUNDAY);
  return calendar.get(Calendar.DAY_OF_WEEK) - 1;
}
