/** 
 * ?????
 * @return
 */
public static Date getPreviousMonthFirstDay(){
  Calendar lastDate=Calendar.getInstance();
  lastDate.set(Calendar.DATE,1);
  lastDate.add(Calendar.MONTH,-1);
  return getMinTime(lastDate.getTime());
}
