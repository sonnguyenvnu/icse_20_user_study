/** 
 * Extracts duration values from an object of this converter's type, and sets them into the given ReadWritableDuration.
 * @param period  period to get modified
 * @param object  the String to convert, must not be null
 * @param chrono  the chronology to use
 * @return the millisecond duration
 * @throws ClassCastException if the object is invalid
 */
public void setInto(ReadWritablePeriod period,Object object,Chronology chrono){
  String str=(String)object;
  PeriodFormatter parser=ISOPeriodFormat.standard();
  period.clear();
  int pos=parser.parseInto(period,str,0);
  if (pos < str.length()) {
    if (pos < 0) {
      parser.withParseType(period.getPeriodType()).parseMutablePeriod(str);
    }
    throw new IllegalArgumentException("Invalid format: \"" + str + '"');
  }
}
