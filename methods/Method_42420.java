/** 
 * ??????????
 * @param date1
 * @param date2
 * @return
 */
public static long getTimeDiff(Date date1,Date date2){
  if (date1 == null || date1 == null) {
    return 0L;
  }
  long diff=(date1.getTime() - date2.getTime()) > 0 ? (date1.getTime() - date2.getTime()) : (date2.getTime() - date1.getTime());
  return diff;
}
