/** 
 * Extracts interval endpoint values from an object of this converter's type, and sets them into the given ReadWritableInterval.
 * @param writableInterval interval to get modified, not null
 * @param object  the object to convert, which is null
 * @param chrono  the chronology to use, may be null
 * @throws NullPointerException if the interval is null
 */
public void setInto(ReadWritableInterval writableInterval,Object object,Chronology chrono){
  writableInterval.setChronology(chrono);
  long now=DateTimeUtils.currentTimeMillis();
  writableInterval.setInterval(now,now);
}
