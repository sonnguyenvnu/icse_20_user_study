/** 
 * Return the so far accumulated objects, but do not deliver them to the target consumer anymore.
 * @return A list of accumulated objects
 */
public List<TYPE> join(){
  ScheduledFuture<?> scheduledFuture=this.future;
  if (scheduledFuture != null && !scheduledFuture.isDone()) {
    scheduledFuture.cancel(false);
  }
  List<TYPE> lqueue=new ArrayList<>();
synchronized (queue) {
    lqueue.addAll(queue);
    queue.clear();
  }
  return lqueue;
}
