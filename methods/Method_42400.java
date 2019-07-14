/** 
 * ??????
 * @param date
 * @return
 */
public static Date getFirstDateOfMonth(Date date){
  Calendar c=Calendar.getInstance();
  c.setTime(date);
  c.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));
  return c.getTime();
}
