/** 
 * ?????????
 * @param date
 * @return
 */
public static Date getWeekStart(Date date){
  Calendar calendar=Calendar.getInstance();
  calendar.setTime(date);
  calendar.get(Calendar.WEEK_OF_YEAR);
  int firstDay=calendar.getFirstDayOfWeek();
  calendar.set(Calendar.DAY_OF_WEEK,firstDay);
  calendar.set(Calendar.HOUR_OF_DAY,0);
  calendar.set(Calendar.MINUTE,0);
  calendar.set(Calendar.SECOND,0);
  calendar.set(Calendar.MILLISECOND,0);
  return calendar.getTime();
}
