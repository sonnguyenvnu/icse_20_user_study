public void unsubscribe(){
  Subscription s=rollingDistributionSubscription.get();
  if (s != null) {
    s.unsubscribe();
    rollingDistributionSubscription.compareAndSet(s,null);
  }
}
