/** 
 * ?????? yyyy?mm?dd?hh?mm?
 * @param date
 * @return
 */
public static String getWholeTime(long date){
  int[] details=TimeUtil.getWholeDetail(date);
  return details[0] + "?" + details[1] + "?" + details[2] + "?  " + details[3] + "?" + details[4] + "?";
}
