/** 
 * Converts this duration to an Interval ending at the specified instant.
 * @param endInstant  the instant to end the interval at, null means now
 * @return an Interval ending at the specified instant
 */
public Interval toIntervalTo(ReadableInstant endInstant){
  return new Interval(this,endInstant);
}
