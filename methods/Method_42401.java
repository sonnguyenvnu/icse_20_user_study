/** 
 * ???????
 * @param date
 * @return
 */
public static Date getLastDateOfMonth(Date date){
  Calendar c=Calendar.getInstance();
  c.setTime(date);
  c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
  return c.getTime();
}
