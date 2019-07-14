/** 
 * Returns a copy of this datetime with a different time zone, preserving the field values. <p> This method is useful for finding the millisecond time in another timezone. For example, if this instant holds 12:30 in Europe/London (ie. 12:30Z), the result from this method with Europe/Paris would be 12:30 (ie. 11:30Z). <p> The returned object will be a new instance of the same implementation type. This method changes the time zone and the millisecond instant to keep the field values the same. The returned object will be either be a new instance or <code>this</code>.
 * @param newZone  the new time zone, null means default
 * @return a copy of this datetime with a different time zone
 * @see #withZone
 */
public DateTime withZoneRetainFields(DateTimeZone newZone){
  newZone=DateTimeUtils.getZone(newZone);
  DateTimeZone originalZone=DateTimeUtils.getZone(getZone());
  if (newZone == originalZone) {
    return this;
  }
  long millis=originalZone.getMillisKeepLocal(newZone,getMillis());
  return new DateTime(millis,getChronology().withZone(newZone));
}
