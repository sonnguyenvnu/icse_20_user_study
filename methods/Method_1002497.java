private <U>Collection<U> reap(Queue<TimedObject<U>> queue,long timeout){
  List<U> toReap=new ArrayList<U>();
  long now=System.currentTimeMillis();
  long target=now - timeout;
synchronized (_lock) {
    int excess=_poolSize - _minSize;
    for (TimedObject<U> p; (p=queue.peek()) != null && p.getTime() < target && excess > 0; excess--) {
      toReap.add(queue.poll().get());
      _statsTracker.incrementTimedOut();
    }
  }
  return toReap;
}
