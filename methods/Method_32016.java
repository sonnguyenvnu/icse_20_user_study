/** 
 * Gets a debugging toString.
 * @return a debugging string
 */
public String toString(){
  String str="ISOChronology";
  DateTimeZone zone=getZone();
  if (zone != null) {
    str=str + '[' + zone.getID() + ']';
  }
  return str;
}
