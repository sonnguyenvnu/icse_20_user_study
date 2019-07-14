/** 
 * Does this partial match the specified instant. <p> A match occurs when all the fields of this partial are the same as the corresponding fields on the specified instant.
 * @param instant  an instant to check against, null means now in default zone
 * @return true if this partial matches the specified instant
 */
public boolean isMatch(ReadableInstant instant){
  long millis=DateTimeUtils.getInstantMillis(instant);
  Chronology chrono=DateTimeUtils.getInstantChronology(instant);
  for (int i=0; i < iTypes.length; i++) {
    int value=iTypes[i].getField(chrono).get(millis);
    if (value != iValues[i]) {
      return false;
    }
  }
  return true;
}
