private static HystrixCommandKey initCommandKey(final HystrixCommandKey fromConstructor,Class<?> clazz){
  if (fromConstructor == null || fromConstructor.name().trim().equals("")) {
    final String keyName=getDefaultNameFromClass(clazz);
    return HystrixCommandKey.Factory.asKey(keyName);
  }
 else {
    return fromConstructor;
  }
}
