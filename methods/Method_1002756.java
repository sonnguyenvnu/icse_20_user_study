@Override void request(long n){
  assert subscription != null;
  if (subscription.needsDirectInvocation()) {
    doRequest(n);
  }
 else {
    subscription.executor().execute(() -> doRequest(n));
  }
}
