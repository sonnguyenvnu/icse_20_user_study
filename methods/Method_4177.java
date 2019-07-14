/** 
 * Returns whether this instance has a timestamp that can be used to calculate the audio track position. If  {@code true}, call  {@link #getTimestampSystemTimeUs()} and {@link #getTimestampSystemTimeUs()} to access the timestamp.
 */
public boolean hasTimestamp(){
  return state == STATE_TIMESTAMP || state == STATE_TIMESTAMP_ADVANCING;
}
