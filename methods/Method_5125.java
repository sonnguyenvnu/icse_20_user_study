/** 
 * Returns a  {@link MediaSourceEventListener.EventDispatcher} which dispatches all events to theregistered listeners with the specified media period id.
 * @param mediaPeriodId The {@link MediaPeriodId} to be reported with the events. May be null, ifthe events do not belong to a specific media period.
 * @return An event dispatcher with pre-configured media period id.
 */
protected final MediaSourceEventListener.EventDispatcher createEventDispatcher(@Nullable MediaPeriodId mediaPeriodId){
  return eventDispatcher.withParameters(0,mediaPeriodId,0);
}
