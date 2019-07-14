/** 
 * Gets the millisecond instant in another zone keeping the same local time. <p> The conversion is performed by converting the specified UTC millis to local millis in this zone, then converting back to UTC millis in the new zone.
 * @param newZone  the new zone, null means default
 * @param oldInstant  the UTC millisecond instant to convert
 * @return the UTC millisecond instant with the same local time in the new zone
 */
public long getMillisKeepLocal(DateTimeZone newZone,long oldInstant){
  if (newZone == null) {
    newZone=DateTimeZone.getDefault();
  }
  if (newZone == this) {
    return oldInstant;
  }
  long instantLocal=convertUTCToLocal(oldInstant);
  return newZone.convertLocalToUTC(instantLocal,false,oldInstant);
}
