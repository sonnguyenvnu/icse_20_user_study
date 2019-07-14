/** 
 * Returns the media time in microseconds that will elapse in  {@code timeMs} milliseconds ofwallclock time.
 * @param timeMs The time to scale, in milliseconds.
 * @return The scaled time, in microseconds.
 */
public long getMediaTimeUsForPlayoutTimeMs(long timeMs){
  return timeMs * scaledUsPerMs;
}
