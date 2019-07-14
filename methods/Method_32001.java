/** 
 * Gets the Chronology in a specific time zone.
 * @param zone  the zone to get the chronology in, null is default
 * @return the chronology
 */
public Chronology withZone(DateTimeZone zone){
  if (zone == null) {
    zone=DateTimeZone.getDefault();
  }
  if (zone == getZone()) {
    return this;
  }
  return getInstance(zone);
}
