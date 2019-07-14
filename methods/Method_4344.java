/** 
 * Resets the position of the standalone fallback clock.
 * @param positionUs The position to set in microseconds.
 */
public void resetPosition(long positionUs){
  standaloneMediaClock.resetPosition(positionUs);
}
