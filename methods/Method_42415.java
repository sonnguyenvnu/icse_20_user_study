/** 
 * ??????
 * @param dt
 * @return
 */
public static Date getMaxTime(Date dt){
  Date dt1=null;
  Calendar ca=Calendar.getInstance();
  ca.setTime(dt);
  ca.add(Calendar.DAY_OF_MONTH,1);
  dt1=ca.getTime();
  dt1=DateUtils.getMinTime(dt1);
  ca.setTime(dt1);
  ca.add(Calendar.SECOND,-1);
  dt1=ca.getTime();
  return dt1;
}
