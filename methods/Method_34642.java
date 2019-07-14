public Observable<Map<HystrixCollapserKey,HystrixCollapserConfiguration>> observeCollapserConfiguration(){
  return allConfigurationStream.map(getOnlyCollapserConfig);
}
