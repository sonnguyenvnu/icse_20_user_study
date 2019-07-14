private static HystrixProperty<Integer> getProperty(String propertyPrefix,String instanceProperty,Integer defaultValue){
  return forInteger().add(propertyPrefix + ".timer.threadpool.default." + instanceProperty,defaultValue).build();
}
