public static Func0<Integer> getCurrentConcurrencyThunk(final HystrixThreadPoolKey threadPoolKey){
  return new Func0<Integer>(){
    @Override public Integer call(){
      return HystrixThreadPoolMetrics.getInstance(threadPoolKey).concurrentExecutionCount.get();
    }
  }
;
}
