public HystrixObservableCommand.Setter buildObservableCommandSetter(){
  HystrixObservableCommand.Setter setter=HystrixObservableCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey)).andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));
  try {
    setter.andCommandPropertiesDefaults(HystrixPropertiesManager.initializeCommandProperties(commandProperties));
  }
 catch (  IllegalArgumentException e) {
    throw new HystrixPropertyException("Failed to set Command properties. " + getInfo(),e);
  }
  return setter;
}
