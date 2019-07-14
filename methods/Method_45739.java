/** 
 * ???????
 * @param dateStr ?????
 * @return ???
 * @throws ParseException ????
 */
public static Long strToLong(String dateStr) throws ParseException {
  return strToDate(dateStr).getTime();
}
