/** 
 * Extracts the chronology from an object of this converter's type where the chronology is specified. <p> This implementation returns the chronology specified, or the ISO chronology in the default zone if null passed in.
 * @param object  the object to convert
 * @param chrono  the chronology to use, null means ISO default
 * @return the chronology, never null
 */
public Chronology getChronology(Object object,Chronology chrono){
  return DateTimeUtils.getChronology(chrono);
}
