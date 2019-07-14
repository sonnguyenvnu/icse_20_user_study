/** 
 * Converts this object to an Interval representing the whole month. <p> The interval will use the chronology of the year-month in the specified zone. <p> This instance is immutable and unaffected by this method call.
 * @param zone  the zone to get the Interval in, null means default
 * @return an interval over the month, never null
 */
public Interval toInterval(DateTimeZone zone){
  zone=DateTimeUtils.getZone(zone);
  DateTime start=toLocalDate(1).toDateTimeAtStartOfDay(zone);
  DateTime end=plusMonths(1).toLocalDate(1).toDateTimeAtStartOfDay(zone);
  return new Interval(start,end);
}
