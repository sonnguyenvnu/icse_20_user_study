@Override public Publisher<R> apply(Flowable<T> upstream){
  return new FlowableSwitchFlatMap<T,R>(upstream,mapper,maxActive,bufferSize);
}
