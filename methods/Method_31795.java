/** 
 * Is the length of this duration equal to the duration passed in. <p> The comparison takes into account the sign. As such, a duration of 5 seconds is not equal to a duration of <i>minus</i> 5 seconds.
 * @param duration  another duration to compare to, null means zero milliseconds
 * @return true if this duration is equal to than the duration passed in
 */
public boolean isEqual(ReadableDuration duration){
  if (duration == null) {
    duration=Duration.ZERO;
  }
  return compareTo(duration) == 0;
}
