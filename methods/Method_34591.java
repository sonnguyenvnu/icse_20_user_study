private static HystrixThreadPoolKey initThreadPoolKey(HystrixThreadPoolKey threadPoolKey,HystrixCommandGroupKey groupKey,String threadPoolKeyOverride){
  if (threadPoolKeyOverride == null) {
    if (threadPoolKey == null) {
      return HystrixThreadPoolKey.Factory.asKey(groupKey.name());
    }
 else {
      return threadPoolKey;
    }
  }
 else {
    return HystrixThreadPoolKey.Factory.asKey(threadPoolKeyOverride);
  }
}
