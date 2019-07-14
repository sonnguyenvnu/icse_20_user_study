/** 
 * Post a list of events to the bus, called by event sender
 * @param eventList TangramOp1 object list
 * @return Return true if the events are successfully enqueued into the event queue.
 */
public boolean post(@NonNull List<Event> eventList){
  return mDispatcher.enqueue(eventList);
}
