/** 
 * Records the time to execute put operations.
 * @param durationNanos the amount of time in nanoseconds
 */
public void recordPutTime(long durationNanos){
  if (enabled && (durationNanos != 0)) {
    putTimeNanos.add(durationNanos);
  }
}
