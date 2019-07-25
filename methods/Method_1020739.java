@Override public Publisher<T> apply(Flowable<T> upstream){
  return new FlowableEvery<T>(upstream,keep);
}
