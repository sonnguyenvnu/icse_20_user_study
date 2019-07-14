/** 
 * Return a printable representation of the date.
 * @param cal calendar to convert to a string
 * @return a printable string.
 */
public static final String prettyString(Calendar cal){
  if (cal == null) {
    return null;
  }
  final int calYear=cal.get(Calendar.YEAR);
  final int calMonth=cal.get(Calendar.MONTH);
  final int calDay=cal.get(Calendar.DATE);
  boolean needSpace=false;
  StringBuffer buf=new StringBuffer();
  if (calMonth >= 0 && calMonth < MONTHS.length) {
    if (needSpace) {
      buf.append(' ');
    }
    buf.append(MONTHS[calMonth][1]);
    needSpace=true;
  }
  if (calDay > 0) {
    if (needSpace) {
      buf.append(' ');
    }
    buf.append(calDay);
    if (calYear > UNSET) {
      buf.append(',');
    }
    needSpace=true;
  }
  if (calYear > UNSET) {
    if (needSpace) {
      buf.append(' ');
    }
    buf.append(calYear);
  }
  appendTimeString(buf,cal,needSpace);
  return buf.toString();
}
