/** 
 * Converts this duration to an Interval starting at the specified instant.
 * @param startInstant  the instant to start the interval at, null means now
 * @return an Interval starting at the specified instant
 */
public Interval toIntervalFrom(ReadableInstant startInstant){
  return new Interval(startInstant,this);
}
