/** 
 * Gets the chronology of the instant, which is ISO in the UTC zone. <p> This method returns  {@link ISOChronology#getInstanceUTC()} whichcorresponds to the definition of the Java epoch 1970-01-01T00:00:00Z.
 * @return ISO in the UTC zone
 */
public Chronology getChronology(){
  return ISOChronology.getInstanceUTC();
}
