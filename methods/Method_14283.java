/** 
 * Convert the supplied month name to its numeric representation. <br> For example, <tt>"January"</tt> (or any substring) would return <tt>1</tt> and <tt>"December"</tt> would return <tt>12</tt>.
 * @param str month name
 * @return the numeric month, or <tt>CalendarParser.UNSET</tt> if thesupplied string is not a valid month name.
 */
public static int monthNameToNumber(String str){
  if (str != null && str.length() >= 3) {
    String lstr=str.toLowerCase();
    for (int i=0; i < MONTHS.length; i++) {
      if (lstr.startsWith(MONTHS[i][0]) || MONTHS[i][1].toLowerCase().startsWith(lstr)) {
        return i + 1;
      }
    }
  }
  return UNSET;
}
