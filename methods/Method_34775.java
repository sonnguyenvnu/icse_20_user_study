public static HystrixThreadEventStream getInstance(){
  return threadLocalStreams.get();
}
