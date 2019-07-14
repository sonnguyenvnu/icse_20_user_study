/** 
 * ??day???????
 * @param date
 * @return
 */
public static Date getDayStart(Date date){
  Calendar calendar=Calendar.getInstance();
  calendar.setTime(date);
  calendar.set(Calendar.HOUR_OF_DAY,0);
  calendar.set(Calendar.MINUTE,0);
  calendar.set(Calendar.SECOND,0);
  calendar.set(Calendar.MILLISECOND,0);
  return calendar.getTime();
}
