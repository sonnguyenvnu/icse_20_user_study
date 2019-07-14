/** 
 * Returns the period holder in the front of the queue which is the playing period holder when playing, or null if the queue is empty.
 */
public MediaPeriodHolder getFrontPeriod(){
  return hasPlayingPeriod() ? playing : loading;
}
