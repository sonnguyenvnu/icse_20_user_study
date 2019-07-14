/** 
 * Returns the elapsed time since initial execution began.
 */
public Duration getElapsedTime(){
  return Duration.ofNanos(System.nanoTime() - startTime.toNanos());
}
