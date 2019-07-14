private static <T>HystrixDynamicProperty<T> getDynamicProperty(String propName,T defaultValue,Class<T> type){
  HystrixDynamicProperties properties=HystrixPlugins.getInstance().getDynamicProperties();
  HystrixDynamicProperty<T> p=HystrixDynamicProperties.Util.getProperty(properties,propName,defaultValue,type);
  return p;
}
