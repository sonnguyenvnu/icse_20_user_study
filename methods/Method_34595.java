private static HystrixThreadPool initThreadPool(HystrixThreadPool fromConstructor,HystrixThreadPoolKey threadPoolKey,HystrixThreadPoolProperties.Setter threadPoolPropertiesDefaults){
  if (fromConstructor == null) {
    return HystrixThreadPool.Factory.getInstance(threadPoolKey,threadPoolPropertiesDefaults);
  }
 else {
    return fromConstructor;
  }
}
