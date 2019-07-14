/** 
 * Returns whether the buffer being processed should be dropped.
 * @param earlyUs The time until the buffer should be presented in microseconds. A negative valueindicates that the buffer is late.
 * @param elapsedRealtimeUs {@link android.os.SystemClock#elapsedRealtime()} in microseconds,measured at the start of the current iteration of the rendering loop.
 */
protected boolean shouldDropOutputBuffer(long earlyUs,long elapsedRealtimeUs){
  return isBufferLate(earlyUs);
}
