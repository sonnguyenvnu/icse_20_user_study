public Observable<Map<HystrixThreadPoolKey,HystrixThreadPoolUtilization>> observeThreadPoolUtilization(){
  return allUtilizationStream.map(getOnlyThreadPoolUtilization);
}
