private static HystrixCommandProperties initCommandProperties(HystrixCommandKey commandKey,HystrixPropertiesStrategy propertiesStrategy,HystrixCommandProperties.Setter commandPropertiesDefaults){
  if (propertiesStrategy == null) {
    return HystrixPropertiesFactory.getCommandProperties(commandKey,commandPropertiesDefaults);
  }
 else {
    return propertiesStrategy.getCommandProperties(commandKey,commandPropertiesDefaults);
  }
}
