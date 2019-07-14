public Observable<Map<HystrixThreadPoolKey,HystrixThreadPoolConfiguration>> observeThreadPoolConfiguration(){
  return allConfigurationStream.map(getOnlyThreadPoolConfig);
}
