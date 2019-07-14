/** 
 * @return The total run time in millis of the stop watch between start and stop calls.
 */
public long getTotalTimeMillis(){
  long duration=stop - start;
  return TimeUnit.NANOSECONDS.toMillis(duration);
}
