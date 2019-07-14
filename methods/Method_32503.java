/** 
 * Handle broken serialization from other tools.
 * @return the resolved object, not null
 */
private Object readResolve(){
  if (iChronology == null) {
    return new LocalTime(iLocalMillis,ISOChronology.getInstanceUTC());
  }
  if (DateTimeZone.UTC.equals(iChronology.getZone()) == false) {
    return new LocalTime(iLocalMillis,iChronology.withUTC());
  }
  return this;
}
