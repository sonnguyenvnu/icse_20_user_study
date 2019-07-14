/** 
 * ?????
 * @param date
 * @return
 */
public static Date getFirstDayOfMonth(Date date){
  Calendar calendar=Calendar.getInstance();
  calendar.setTime(date);
  calendar.set(Calendar.DATE,calendar.getActualMinimum(Calendar.DATE));
  return calendar.getTime();
}
