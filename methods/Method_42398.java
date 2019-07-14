/** 
 * ?????????
 * @param date
 * @return
 */
public static Date getYearEnd(Date date){
  Calendar calendar=Calendar.getInstance();
  calendar.setTime(date);
  calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
  calendar.set(Calendar.MONTH,11);
  calendar.set(Calendar.DAY_OF_MONTH,31);
  calendar.set(Calendar.HOUR_OF_DAY,23);
  calendar.set(Calendar.MINUTE,59);
  calendar.set(Calendar.SECOND,59);
  calendar.set(Calendar.MILLISECOND,0);
  return calendar.getTime();
}
