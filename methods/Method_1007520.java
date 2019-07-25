public void subscribe(boolean avail,Task t,boolean set) throws Pausable {
  EventSubscriber subscriber=avail ? sink.getAndSet(null) : null;
  if (subscriber != null)   subscriber.onEvent(this,messageAvailable);
  if (set) {
    srcs.set(t);
    Task.pause(this);
    removeSpaceAvailableListener(t);
  }
}
