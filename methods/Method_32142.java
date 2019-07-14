/** 
 * Formats a timezone offset string. <p> This method is kept separate from the formatting classes to speed and simplify startup and classloading.
 * @param offset  the offset in milliseconds
 * @return the time zone string
 */
private static String printOffset(int offset){
  StringBuffer buf=new StringBuffer();
  if (offset >= 0) {
    buf.append('+');
  }
 else {
    buf.append('-');
    offset=-offset;
  }
  int hours=offset / DateTimeConstants.MILLIS_PER_HOUR;
  FormatUtils.appendPaddedInteger(buf,hours,2);
  offset-=hours * (int)DateTimeConstants.MILLIS_PER_HOUR;
  int minutes=offset / DateTimeConstants.MILLIS_PER_MINUTE;
  buf.append(':');
  FormatUtils.appendPaddedInteger(buf,minutes,2);
  offset-=minutes * DateTimeConstants.MILLIS_PER_MINUTE;
  if (offset == 0) {
    return buf.toString();
  }
  int seconds=offset / DateTimeConstants.MILLIS_PER_SECOND;
  buf.append(':');
  FormatUtils.appendPaddedInteger(buf,seconds,2);
  offset-=seconds * DateTimeConstants.MILLIS_PER_SECOND;
  if (offset == 0) {
    return buf.toString();
  }
  buf.append('.');
  FormatUtils.appendPaddedInteger(buf,offset,3);
  return buf.toString();
}
