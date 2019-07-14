/** 
 * Returns a copy of this datetime with the specified date, retaining the time fields. <p> If the date is already the date passed in, then <code>this</code> is returned. <p> To set a single field use the properties, for example: <pre> DateTime set = monthOfYear().setCopy(6); </pre> <p> If the time is invalid on the new date due to the time-zone, the time will be adjusted. <p> This instance is immutable and unaffected by this method call.
 * @param year  the new year value
 * @param monthOfYear  the new monthOfYear value
 * @param dayOfMonth  the new dayOfMonth value
 * @return a copy of this datetime with a different date
 * @throws IllegalArgumentException if any value if invalid
 */
public DateTime withDate(int year,int monthOfYear,int dayOfMonth){
  Chronology chrono=getChronology();
  long localInstant=chrono.withUTC().getDateTimeMillis(year,monthOfYear,dayOfMonth,getMillisOfDay());
  return withMillis(chrono.getZone().convertLocalToUTC(localInstant,false,getMillis()));
}
