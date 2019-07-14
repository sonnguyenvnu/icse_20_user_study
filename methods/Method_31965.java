/** 
 * Gets a debugging toString.
 * @return a debugging string
 */
public String toString(){
  StringBuffer sb=new StringBuffer(60);
  sb.append("GJChronology");
  sb.append('[');
  sb.append(getZone().getID());
  if (iCutoverMillis != DEFAULT_CUTOVER.getMillis()) {
    sb.append(",cutover=");
    DateTimeFormatter printer;
    if (withUTC().dayOfYear().remainder(iCutoverMillis) == 0) {
      printer=ISODateTimeFormat.date();
    }
 else {
      printer=ISODateTimeFormat.dateTime();
    }
    printer.withChronology(withUTC()).printTo(sb,iCutoverMillis);
  }
  if (getMinimumDaysInFirstWeek() != 4) {
    sb.append(",mdfw=");
    sb.append(getMinimumDaysInFirstWeek());
  }
  sb.append(']');
  return sb.toString();
}
