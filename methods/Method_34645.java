private static HystrixCollapserConfiguration sampleCollapserConfiguration(HystrixCollapserKey collapserKey,HystrixCollapserProperties collapserProperties){
  return HystrixCollapserConfiguration.sample(collapserKey,collapserProperties);
}