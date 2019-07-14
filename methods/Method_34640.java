public Observable<Map<HystrixCommandKey,HystrixCommandConfiguration>> observeCommandConfiguration(){
  return allConfigurationStream.map(getOnlyCommandConfig);
}
