protected String beanNamePrefix(){
  final String appName=appNameSupplier.get();
  return appName + ".madvoc.";
}
