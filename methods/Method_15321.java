/** 
 * ????
 * @param date
 * @param needYear
 * @return
 */
public static String getBirthday(long date,boolean needYear){
  int[] details=TimeUtil.getWholeDetail(date);
  if (needYear) {
    return details[0] + "?" + details[1] + "?" + details[2] + "?";
  }
  return details[1] + "?" + details[2] + "?";
}
