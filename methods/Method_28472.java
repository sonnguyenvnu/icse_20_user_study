/** 
 * Get the short month name for a certain date.
 * @param year The year of the date.
 * @param month The month of the date.
 * @param day The day of the date.
 * @return The short name of the month.
 */
@SuppressLint("WrongConstant") public static String getShortMonthName(int year,int month,int day){
  Calendar calendar=Calendar.getInstance();
  calendar.set(year,month - 1,day);
  calendar.add(Calendar.SECOND,0);
  SimpleDateFormat month_date=new SimpleDateFormat("MMM",Locale.US);
  return month_date.format(calendar.getTime());
}
