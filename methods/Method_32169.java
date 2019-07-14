/** 
 * Creates a new Duration instance with a different millisecond length.
 * @param duration  the new length of the duration
 * @return the new duration instance
 */
public Duration withMillis(long duration){
  if (duration == getMillis()) {
    return this;
  }
  return new Duration(duration);
}
