/** 
 * ?????????
 * @param date
 * @return
 */
public static Date getWeekEnd(Date date){
  Calendar calendar=Calendar.getInstance();
  calendar.setTime(date);
  calendar.get(Calendar.WEEK_OF_YEAR);
  int firstDay=calendar.getFirstDayOfWeek();
  calendar.set(Calendar.DAY_OF_WEEK,8 - firstDay);
  calendar.set(Calendar.HOUR_OF_DAY,23);
  calendar.set(Calendar.MINUTE,59);
  calendar.set(Calendar.SECOND,59);
  calendar.set(Calendar.MILLISECOND,0);
  return calendar.getTime();
}
