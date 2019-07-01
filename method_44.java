/** 
 * TODO: Potential improvement: if StreamSortHandler is expensive, we can use DISRUPTOR to buffer.
 * @param event StreamEvent
 */
public void _XXXXX_(PartitionedEvent event){
  this.context.counter().incr("receive_count");
  if (!dispatchToSortHandler(event)) {
    this.context.counter().incr("direct_count");
    outputCollector.emit(event);
  }
  this.context.counter().incr("sort_count");
  streamTimeClockManager.onTimeUpdate(event.getStreamId(),event.getTimestamp());
}