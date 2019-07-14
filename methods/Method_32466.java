/** 
 * Converts this object to an Interval representing the whole day. <p> The interval may have more or less than 24 hours if this is a daylight savings cutover date. <p> This instance is immutable and unaffected by this method call.
 * @param zone  the zone to get the Interval in, null means default
 * @return a interval over the day
 */
public Interval toInterval(DateTimeZone zone){
  zone=DateTimeUtils.getZone(zone);
  DateTime start=toDateTimeAtStartOfDay(zone);
  DateTime end=plusDays(1).toDateTimeAtStartOfDay(zone);
  return new Interval(start,end);
}
