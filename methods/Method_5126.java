/** 
 * Returns a  {@link MediaSourceEventListener.EventDispatcher} which dispatches all events to theregistered listeners with the specified media period id and time offset.
 * @param mediaPeriodId The {@link MediaPeriodId} to be reported with the events.
 * @param mediaTimeOffsetMs The offset to be added to all media times, in milliseconds.
 * @return An event dispatcher with pre-configured media period id and time offset.
 */
protected final MediaSourceEventListener.EventDispatcher createEventDispatcher(MediaPeriodId mediaPeriodId,long mediaTimeOffsetMs){
  Assertions.checkArgument(mediaPeriodId != null);
  return eventDispatcher.withParameters(0,mediaPeriodId,mediaTimeOffsetMs);
}
