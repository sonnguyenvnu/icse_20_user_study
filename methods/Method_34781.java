@Override public Observable<HystrixCommandCompletion> observe(){
  return readOnlyStream;
}
