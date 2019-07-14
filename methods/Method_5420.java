/** 
 * Returns the result of adding the duration of the playlist to its start time.
 */
public long getEndTimeUs(){
  return startTimeUs + durationUs;
}
