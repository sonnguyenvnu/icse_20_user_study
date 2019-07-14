/** 
 * Is the length of this duration longer than the duration passed in. <p> The comparison takes into account the sign. As such, a duration of 5 seconds is longer than a duration of <i>minus</i> 7 seconds.
 * @param duration  another duration to compare to, null means zero milliseconds
 * @return true if this duration is strictly longer than the duration passed in
 */
public boolean isLongerThan(ReadableDuration duration){
  if (duration == null) {
    duration=Duration.ZERO;
  }
  return compareTo(duration) > 0;
}
