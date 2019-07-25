@Override public Publisher<T> apply(Flowable<T> upstream){
  return new FlowableSwitchIfEmptyManyArray<T>(upstream,alternatives);
}
