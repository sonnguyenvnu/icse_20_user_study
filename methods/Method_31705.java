/** 
 * Converts this date into a YYYY-MM-dd string.
 * @param date The date.
 * @return The matching string.
 */
public static String toDateString(Date date){
  GregorianCalendar calendar=new GregorianCalendar();
  calendar.setTime(date);
  String year="" + calendar.get(Calendar.YEAR);
  String month=StringUtils.trimOrLeftPad("" + (calendar.get(Calendar.MONTH) + 1),2,'0');
  String day=StringUtils.trimOrLeftPad("" + calendar.get(Calendar.DAY_OF_MONTH),2,'0');
  return year + "-" + month + "-" + day;
}
