/** 
 * Offsets a timestamp in microseconds.
 * @param timeUs The timestamp to adjust in microseconds.
 * @return The adjusted timestamp in microseconds.
 */
public long adjustSampleTimestamp(long timeUs){
  if (timeUs == C.TIME_UNSET) {
    return C.TIME_UNSET;
  }
  if (lastSampleTimestampUs != C.TIME_UNSET) {
    lastSampleTimestampUs=timeUs;
  }
 else {
    if (firstSampleTimestampUs != DO_NOT_OFFSET) {
      timestampOffsetUs=firstSampleTimestampUs - timeUs;
    }
synchronized (this) {
      lastSampleTimestampUs=timeUs;
      notifyAll();
    }
  }
  return timeUs + timestampOffsetUs;
}
