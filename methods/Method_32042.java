/** 
 * @param localInstant  the instant from 1970-01-01T00:00:00 local time
 * @return the instant from 1970-01-01T00:00:00Z
 */
private long localToUTC(long localInstant){
  if (localInstant == Long.MAX_VALUE) {
    return Long.MAX_VALUE;
  }
 else   if (localInstant == Long.MIN_VALUE) {
    return Long.MIN_VALUE;
  }
  DateTimeZone zone=getZone();
  int offset=zone.getOffsetFromLocal(localInstant);
  long utcInstant=localInstant - offset;
  if (localInstant > NEAR_ZERO && utcInstant < 0) {
    return Long.MAX_VALUE;
  }
 else   if (localInstant < -NEAR_ZERO && utcInstant > 0) {
    return Long.MIN_VALUE;
  }
  int offsetBasedOnUtc=zone.getOffset(utcInstant);
  if (offset != offsetBasedOnUtc) {
    throw new IllegalInstantException(localInstant,zone.getID());
  }
  return utcInstant;
}
