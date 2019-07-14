/** 
 * Continues reading from the next period holder in the queue.
 * @return The updated reading period holder.
 */
public MediaPeriodHolder advanceReadingPeriod(){
  Assertions.checkState(reading != null && reading.getNext() != null);
  reading=reading.getNext();
  return reading;
}
