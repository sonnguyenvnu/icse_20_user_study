public void startCachingStreamValuesIfUnstarted(){
  if (rollingMaxSubscription.get() == null) {
    Subscription candidateSubscription=observe().subscribe(rollingMax);
    if (rollingMaxSubscription.compareAndSet(null,candidateSubscription)) {
    }
 else {
      candidateSubscription.unsubscribe();
    }
  }
}
