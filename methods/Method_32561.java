/** 
 * Set the time from another instant. The date part of this object will be unaffected.
 * @param instant  an instant to copy the time from, date part ignored
 * @throws IllegalArgumentException if the object is invalid
 */
public void setTime(final ReadableInstant instant){
  long instantMillis=DateTimeUtils.getInstantMillis(instant);
  Chronology instantChrono=DateTimeUtils.getInstantChronology(instant);
  DateTimeZone zone=instantChrono.getZone();
  if (zone != null) {
    instantMillis=zone.getMillisKeepLocal(DateTimeZone.UTC,instantMillis);
  }
  setTime(instantMillis);
}
