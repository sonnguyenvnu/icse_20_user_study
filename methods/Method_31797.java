/** 
 * Is the length of this duration shorter than the duration passed in. <p> The comparison takes into account the sign. As such, a duration of <i>minus</i> 5 seconds is shorter than a duration of 3 seconds.
 * @param duration  another duration to compare to, null means zero milliseconds
 * @return true if this duration is strictly shorter than the duration passed in
 */
public boolean isShorterThan(ReadableDuration duration){
  if (duration == null) {
    duration=Duration.ZERO;
  }
  return compareTo(duration) < 0;
}
