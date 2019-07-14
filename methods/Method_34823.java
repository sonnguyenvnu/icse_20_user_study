private static HystrixDynamicProperties resolveDynamicProperties(ClassLoader classLoader,LoggerSupplier logSupplier){
  HystrixDynamicProperties hp=getPluginImplementationViaProperties(HystrixDynamicProperties.class,HystrixDynamicPropertiesSystemProperties.getInstance());
  if (hp != null) {
    logSupplier.getLogger().debug("Created HystrixDynamicProperties instance from System property named " + "\"hystrix.plugin.HystrixDynamicProperties.implementation\". Using class: {}",hp.getClass().getCanonicalName());
    return hp;
  }
  hp=findService(HystrixDynamicProperties.class,classLoader);
  if (hp != null) {
    logSupplier.getLogger().debug("Created HystrixDynamicProperties instance by loading from ServiceLoader. Using class: {}",hp.getClass().getCanonicalName());
    return hp;
  }
  hp=HystrixArchaiusHelper.createArchaiusDynamicProperties();
  if (hp != null) {
    logSupplier.getLogger().debug("Created HystrixDynamicProperties. Using class : {}",hp.getClass().getCanonicalName());
    return hp;
  }
  hp=HystrixDynamicPropertiesSystemProperties.getInstance();
  logSupplier.getLogger().info("Using System Properties for HystrixDynamicProperties! Using class: {}",hp.getClass().getCanonicalName());
  return hp;
}
