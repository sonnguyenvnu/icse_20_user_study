/** 
 * Translate a string representation of an ordinal number to the appropriate numeric value.<br> For example, <tt>"1st"</tt> would return <tt>1</tt>, <tt>"23rd"</tt> would return <tt>23</tt>, etc.
 * @param str ordinal string
 * @return the numeric value of the ordinal number, or<tt>CalendarParser.UNSET</tt> if the supplied string is not a valid ordinal number.
 */
private static final int getOrdinalNumber(String str){
  final int len=(str == null ? 0 : str.length());
  if (len >= 3) {
    String suffix=str.substring(len - 2);
    if (suffix.equalsIgnoreCase("st") || suffix.equalsIgnoreCase("nd") || suffix.equalsIgnoreCase("rd") || suffix.equalsIgnoreCase("th")) {
      try {
        return Integer.parseInt(str.substring(0,len - 2));
      }
 catch (      NumberFormatException nfe) {
      }
    }
  }
  return UNSET;
}
