/** 
 * ????????
 * @param date
 * @return
 */
public static Date getMonthStart(Date date){
  Calendar calendar=Calendar.getInstance();
  calendar.setTime(date);
  calendar.set(Calendar.DAY_OF_MONTH,1);
  calendar.set(Calendar.HOUR_OF_DAY,0);
  calendar.set(Calendar.MINUTE,0);
  calendar.set(Calendar.SECOND,0);
  calendar.set(Calendar.MILLISECOND,0);
  return calendar.getTime();
}
