/** 
 * ??????
 * @param date   ??
 * @param format ?????
 * @return ?????
 */
public static String dateToStr(Date date,String format){
  return new SimpleDateFormat(format).format(date);
}
