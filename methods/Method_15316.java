/** 
 * ???? ???? ? ???
 * @param date
 * @return
 */
public static int[] getDateDetail(Date date){
  return date == null ? null : getDateDetail(date.getTime());
}
