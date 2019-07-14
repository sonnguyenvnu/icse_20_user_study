/** 
 * Get the millis of second property
 * @return the millis of second property
 */
public Property millisOfSecond(){
  return new Property(this,getChronology().millisOfSecond());
}
