/** 
 * Create a new date with this year, month and day.
 * @param year  The year.
 * @param month The month (1-12).
 * @param day   The day (1-31).
 * @return The date.
 */
public static Date toDate(int year,int month,int day){
  return new GregorianCalendar(year,month - 1,day).getTime();
}
