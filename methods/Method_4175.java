/** 
 * Polls the timestamp if required and returns whether it was updated. If  {@code true}, the latest timestamp is available via  {@link #getTimestampSystemTimeUs()} and {@link #getTimestampPositionFrames()}, and the caller should call  {@link #acceptTimestamp()} if thetimestamp was valid, or  {@link #rejectTimestamp()} otherwise. The values returned by {@link #hasTimestamp()} and {@link #isTimestampAdvancing()} may be updated.
 * @param systemTimeUs The current system time, in microseconds.
 * @return Whether the timestamp was updated.
 */
public boolean maybePollTimestamp(long systemTimeUs){
  if (audioTimestamp == null || (systemTimeUs - lastTimestampSampleTimeUs) < sampleIntervalUs) {
    return false;
  }
  lastTimestampSampleTimeUs=systemTimeUs;
  boolean updatedTimestamp=audioTimestamp.maybeUpdateTimestamp();
switch (state) {
case STATE_INITIALIZING:
    if (updatedTimestamp) {
      if (audioTimestamp.getTimestampSystemTimeUs() >= initializeSystemTimeUs) {
        initialTimestampPositionFrames=audioTimestamp.getTimestampPositionFrames();
        updateState(STATE_TIMESTAMP);
      }
 else {
        updatedTimestamp=false;
      }
    }
 else     if (systemTimeUs - initializeSystemTimeUs > INITIALIZING_DURATION_US) {
      updateState(STATE_NO_TIMESTAMP);
    }
  break;
case STATE_TIMESTAMP:
if (updatedTimestamp) {
  long timestampPositionFrames=audioTimestamp.getTimestampPositionFrames();
  if (timestampPositionFrames > initialTimestampPositionFrames) {
    updateState(STATE_TIMESTAMP_ADVANCING);
  }
}
 else {
  reset();
}
break;
case STATE_TIMESTAMP_ADVANCING:
if (!updatedTimestamp) {
reset();
}
break;
case STATE_NO_TIMESTAMP:
if (updatedTimestamp) {
reset();
}
break;
case STATE_ERROR:
break;
default :
throw new IllegalStateException();
}
return updatedTimestamp;
}
