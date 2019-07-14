/** 
 * Add an event to the internal event queue, or process it immediately if the sketch is not currently looping.
 */
public void postEvent(processing.event.Event pe){
  eventQueue.add(pe);
  if (!looping) {
    dequeueEvents();
  }
}
