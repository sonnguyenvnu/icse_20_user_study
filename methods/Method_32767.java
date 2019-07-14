/** 
 * Converts this object to a DateTime using a TimeOfDay to fill in the missing fields. This instance is immutable and unaffected by this method call. <p> The resulting chronology is determined by the chronology of this YearMonthDay plus the time zone. The chronology of the time is ignored - only the field values are used.
 * @param time  the time of day to use, null means current time
 * @param zone  the zone to get the DateTime in, null means default
 * @return the DateTime instance
 */
public DateTime toDateTime(TimeOfDay time,DateTimeZone zone){
  Chronology chrono=getChronology().withZone(zone);
  long instant=DateTimeUtils.currentTimeMillis();
  instant=chrono.set(this,instant);
  if (time != null) {
    instant=chrono.set(time,instant);
  }
  return new DateTime(instant,chrono);
}
