@Override public Publisher<T> apply(Flowable<T> upstream){
  return new FlowableRequestSample<T>(upstream,other);
}
