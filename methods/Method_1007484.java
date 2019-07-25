/** 
 * Non-blocking, nonpausing put.
 * @param eo . If non-null, registers this observer and calls it with an SpaceAvailable event when there's space.
 * @return buffered message if there's one, or null
 */
public boolean put(T msg,EventSubscriber eo){
  boolean ret=true;
  EventSubscriber subscriber;
  if (msg == null)   throw new NullPointerException("Null message supplied to put");
  if (message.compareAndSet(null,msg)) {
    subscriber=sink.get();
  }
 else {
    ret=false;
    subscriber=null;
    if (eo != null) {
      srcs.add(eo);
    }
  }
  if (subscriber != null) {
    if (sink.compareAndSet(subscriber,null)) {
      subscriber.onEvent(this,messageAvailable);
    }
  }
  return ret;
}
