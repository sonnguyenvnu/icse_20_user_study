/** 
 * Creates instance of  {@link HystrixCommand.Setter}.
 * @return the instance of {@link HystrixCommand.Setter}
 */
public HystrixCommand.Setter build() throws HystrixPropertyException {
  HystrixCommand.Setter setter=HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey)).andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));
  if (StringUtils.isNotBlank(threadPoolKey)) {
    setter.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(threadPoolKey));
  }
  try {
    setter.andThreadPoolPropertiesDefaults(HystrixPropertiesManager.initializeThreadPoolProperties(threadPoolProperties));
  }
 catch (  IllegalArgumentException e) {
    throw new HystrixPropertyException("Failed to set Thread Pool properties. " + getInfo(),e);
  }
  try {
    setter.andCommandPropertiesDefaults(HystrixPropertiesManager.initializeCommandProperties(commandProperties));
  }
 catch (  IllegalArgumentException e) {
    throw new HystrixPropertyException("Failed to set Command properties. " + getInfo(),e);
  }
  return setter;
}
