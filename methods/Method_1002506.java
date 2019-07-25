/** 
 * Closes the queue and returns the items enqueued prior to closure. No more items can be enqueued after calling this method, and offer() will return false. It is the caller's responsibility to ensure that close() is called at most once
 * @return the items enqueued prior to closure
 * @throws IllegalStateException if close() has been previously called
 */
public List<T> close(){
  List<T> queue=ensureClosed();
  if (queue == null) {
    throw new IllegalStateException("Queue is already closed");
  }
  return queue;
}
