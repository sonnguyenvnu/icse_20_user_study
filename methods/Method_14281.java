/** 
 * Determine is the supplied string is a value weekday name.
 * @param str weekday name to check
 * @return <tt>true</tt> if the supplied string is a weekday name.
 */
private static final boolean isWeekdayName(String str){
  if (str == null || str.length() < 3) {
    return false;
  }
  String lstr=str.toLowerCase();
  for (  String element : WEEKDAY_NAMES) {
    if (lstr.startsWith(element) || element.toLowerCase().startsWith(lstr)) {
      return true;
    }
  }
  return false;
}
