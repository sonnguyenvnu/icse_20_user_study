/** 
 * Resets the clock's position.
 * @param positionUs The position to set in microseconds.
 */
public void resetPosition(long positionUs){
  baseUs=positionUs;
  if (started) {
    baseElapsedMs=clock.elapsedRealtime();
  }
}
