/** 
 * Serialization singleton
 */
private Object readResolve(){
  return iChronology.yearOfEra();
}
