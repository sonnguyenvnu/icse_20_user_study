/** 
 * Return if the date given is a first day of week
 * @param year The year of the date.
 * @param month The month of the date.
 * @param day The day of the date.
 * @return true or false
 */
@SuppressLint("WrongConstant") public static boolean isFirstDayOfWeek(int year,int month,int day){
  Calendar calendar=Calendar.getInstance();
  calendar.set(year,month - 1,day);
  calendar.add(Calendar.SECOND,0);
  calendar.setFirstDayOfWeek(Calendar.SUNDAY);
  return calendar.get(Calendar.DAY_OF_WEEK) == 1;
}
