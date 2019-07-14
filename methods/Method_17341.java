/** 
 * Records the time to execute remove operations.
 * @param durationNanos the amount of time in nanoseconds
 */
public void recordRemoveTime(long durationNanos){
  if (enabled && (durationNanos != 0)) {
    removeTimeNanos.add(durationNanos);
  }
}
