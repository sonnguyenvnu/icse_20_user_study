/** 
 * Sets the next media period holder in the queue.
 * @param nextMediaPeriodHolder The next holder, or null if this will be the new loading mediaperiod holder at the end of the queue.
 */
public void setNext(@Nullable MediaPeriodHolder nextMediaPeriodHolder){
  if (nextMediaPeriodHolder == next) {
    return;
  }
  disableTrackSelectionsInResult();
  next=nextMediaPeriodHolder;
  enableTrackSelectionsInResult();
}
