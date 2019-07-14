/** 
 * Returns the time to wait before the next execution attempt. Returns  {@code 0} if an execution has not yetoccurred.
 */
public Duration getWaitTime(){
  return Duration.ofNanos(waitNanos);
}
