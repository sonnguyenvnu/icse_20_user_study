/** 
 * Returns a  {@link MediaSourceEventListener.EventDispatcher} which dispatches all events to theregistered listeners with the specified window index, media period id and time offset.
 * @param windowIndex The timeline window index to be reported with the events.
 * @param mediaPeriodId The {@link MediaPeriodId} to be reported with the events. May be null, ifthe events do not belong to a specific media period.
 * @param mediaTimeOffsetMs The offset to be added to all media times, in milliseconds.
 * @return An event dispatcher with pre-configured media period id and time offset.
 */
protected final MediaSourceEventListener.EventDispatcher createEventDispatcher(int windowIndex,@Nullable MediaPeriodId mediaPeriodId,long mediaTimeOffsetMs){
  return eventDispatcher.withParameters(windowIndex,mediaPeriodId,mediaTimeOffsetMs);
}
