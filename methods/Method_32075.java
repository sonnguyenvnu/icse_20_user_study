/** 
 * Extracts interval endpoint values from an object of this converter's type, and sets them into the given ReadWritableInterval.
 * @param writableInterval interval to get modified, not null
 * @param object  the object to convert, must not be null
 * @param chrono  the chronology to use, may be null
 * @throws ClassCastException if the object is invalid
 */
public void setInto(ReadWritableInterval writableInterval,Object object,Chronology chrono){
  ReadableInterval input=(ReadableInterval)object;
  writableInterval.setInterval(input);
  if (chrono != null) {
    writableInterval.setChronology(chrono);
  }
 else {
    writableInterval.setChronology(input.getChronology());
  }
}
