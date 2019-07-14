/** 
 * Advances the timer and evicts entries that have expired.
 * @param currentTimeNanos the current time, in nanoseconds
 */
public void advance(long currentTimeNanos){
  long previousTimeNanos=nanos;
  try {
    nanos=currentTimeNanos;
    for (int i=0; i < SHIFT.length; i++) {
      long previousTicks=(previousTimeNanos >> SHIFT[i]);
      long currentTicks=(currentTimeNanos >> SHIFT[i]);
      if ((currentTicks - previousTicks) <= 0L) {
        break;
      }
      expire(i,previousTicks,currentTicks);
    }
  }
 catch (  Throwable t) {
    nanos=previousTimeNanos;
    throw t;
  }
}
