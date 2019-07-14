/** 
 * Returns a copy of this date with different local millis. <p> The returned object will be a new instance of the same type. Only the millis will change, the chronology is kept. The returned object will be either be a new instance or <code>this</code>.
 * @param newMillis  the new millis, from 1970-01-01T00:00:00
 * @return a copy of this date with different millis
 */
LocalDate withLocalMillis(long newMillis){
  newMillis=iChronology.dayOfMonth().roundFloor(newMillis);
  return (newMillis == getLocalMillis() ? this : new LocalDate(newMillis,getChronology()));
}
