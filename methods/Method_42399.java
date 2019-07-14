/** 
 * ?????
 * @param date
 * @return
 */
public static int getDayOfMonth(Date date){
  Calendar c=Calendar.getInstance();
  c.setTime(date);
  return c.getActualMaximum(Calendar.DAY_OF_MONTH);
}
