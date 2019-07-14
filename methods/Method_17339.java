/** 
 * Records the time to execute get operations. This time does not include the time it takes to load an entry on a cache miss, as specified by the specification.
 * @param durationNanos the amount of time in nanoseconds
 */
public void recordGetTime(long durationNanos){
  if (enabled && (durationNanos != 0)) {
    getTimeNanos.add(durationNanos);
  }
}
