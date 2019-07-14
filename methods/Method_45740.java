/** 
 * ??????
 * @param dateStr ?????
 * @param format  ?????
 * @return ?????
 * @throws ParseException ????
 */
public static Date strToDate(String dateStr,String format) throws ParseException {
  return new SimpleDateFormat(format).parse(dateStr);
}
