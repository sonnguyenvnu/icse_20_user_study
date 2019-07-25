/** 
 * Non-blocking, nonpausing put. 
 * @param eo. If non-null, registers this observer and calls it with an SpaceAvailable event when there's space.
 * @return buffered message if there's one, or null
 * @see #putnb(Object)
 * @see #putb(Object) 
 */
@SuppressWarnings("unchecked") public boolean put(T msg,EventSubscriber eo){
  boolean ret=true;
  EventSubscriber subscriber;
synchronized (this) {
    if (msg == null) {
      throw new NullPointerException("Null message supplied to put");
    }
    int ip=iprod;
    int ic=icons;
    int n=numMsgs;
    if (n == msgs.length) {
      assert ic == ip : "numElements == msgs.length && ic != ip";
      if (n < maxMsgs) {
        T[] newmsgs=(T[])new Object[Math.min(n * 2,maxMsgs)];
        System.arraycopy(msgs,ic,newmsgs,0,n - ic);
        if (ic > 0) {
          System.arraycopy(msgs,0,newmsgs,n - ic,ic);
        }
        msgs=newmsgs;
        ip=n;
        ic=0;
      }
 else {
        ret=false;
      }
    }
    if (ret) {
      numMsgs=n + 1;
      msgs[ip]=msg;
      iprod=(ip + 1) % msgs.length;
      icons=ic;
      subscriber=sink;
      sink=null;
    }
 else {
      subscriber=null;
      if (eo != null) {
        srcs.add(eo);
      }
    }
  }
  if (subscriber != null) {
    subscriber.onEvent(this,messageAvailable);
  }
  return ret;
}
