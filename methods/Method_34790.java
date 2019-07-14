public Observable<Map<HystrixCommandKey,HystrixCommandUtilization>> observeCommandUtilization(){
  return allUtilizationStream.map(getOnlyCommandUtilization);
}
