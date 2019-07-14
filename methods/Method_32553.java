/** 
 * Sets the time zone of the datetime, changing the chronology and millisecond. <p> Changing the zone using this method retains the field values. The millisecond instant is adjusted in the new zone to compensate. <p> If the chronology already has this time zone, no change occurs.
 * @param newZone  the time zone to use, null means default zone
 * @see #setZone
 */
public void setZoneRetainFields(DateTimeZone newZone){
  newZone=DateTimeUtils.getZone(newZone);
  DateTimeZone originalZone=DateTimeUtils.getZone(getZone());
  if (newZone == originalZone) {
    return;
  }
  long millis=originalZone.getMillisKeepLocal(newZone,getMillis());
  setChronology(getChronology().withZone(newZone));
  setMillis(millis);
}
