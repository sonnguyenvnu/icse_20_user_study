@Override public final void request(long n){
  if (n < 1) {
    subscriber.onError(new IllegalArgumentException("3.9 While the Subscription is not cancelled, Subscription.request(long n) MUST throw a java.lang.IllegalArgumentException if the argument is <= 0."));
    cancel();
    return;
  }
  if (!open) {
    if (n == Long.MAX_VALUE) {
      open=true;
      demand.set(Long.MAX_VALUE);
    }
 else {
      long newDemand=demand.addAndGet(n);
      if (newDemand < 1 || newDemand == Long.MAX_VALUE) {
        open=true;
        n=Long.MAX_VALUE;
        demand.set(Long.MAX_VALUE);
      }
    }
    onRequest(n);
  }
}
