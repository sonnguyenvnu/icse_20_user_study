/** 
 * ??????
 * @param dt
 * @return
 */
public static Date getMinTime(Date dt){
  Date dt1=null;
  dt1=DateUtils.getDateByStr(DateUtils.formatDate(dt,"yyyy-MM-dd"));
  return dt1;
}
