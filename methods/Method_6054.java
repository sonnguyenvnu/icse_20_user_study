/** 
 * Returns whether to drop all buffers from the buffer being processed to the keyframe at or after the current playback position, if possible.
 * @param earlyUs The time until the current buffer should be presented in microseconds. Anegative value indicates that the buffer is late.
 * @param elapsedRealtimeUs {@link android.os.SystemClock#elapsedRealtime()} in microseconds,measured at the start of the current iteration of the rendering loop.
 */
protected boolean shouldDropBuffersToKeyframe(long earlyUs,long elapsedRealtimeUs){
  return isBufferVeryLate(earlyUs);
}
