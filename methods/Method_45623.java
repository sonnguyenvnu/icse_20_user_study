private static ExtensionLoader<Filter> buildLoader(){
  return ExtensionLoaderFactory.getExtensionLoader(Filter.class,new ExtensionLoaderListener<Filter>(){
    @Override public void onLoad(    ExtensionClass<Filter> extensionClass){
      Class<? extends Filter> implClass=extensionClass.getClazz();
      AutoActive autoActive=implClass.getAnnotation(AutoActive.class);
      if (autoActive != null) {
        String alias=extensionClass.getAlias();
        if (autoActive.providerSide()) {
          PROVIDER_AUTO_ACTIVES.put(alias,extensionClass);
        }
        if (autoActive.consumerSide()) {
          CONSUMER_AUTO_ACTIVES.put(alias,extensionClass);
        }
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Extension of interface " + Filter.class + ", " + implClass + "(" + alias + ") will auto active");
        }
      }
    }
  }
);
}
