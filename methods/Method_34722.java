private static HystrixProperty<Boolean> getProperty(String propertyPrefix,HystrixThreadPoolKey key,String instanceProperty,Boolean builderOverrideValue,Boolean defaultValue){
  return forBoolean().add(propertyPrefix + ".threadpool." + key.name() + "." + instanceProperty,builderOverrideValue).add(propertyPrefix + ".threadpool.default." + instanceProperty,defaultValue).build();
}
