private static ExtensionLoader<Router> buildLoader(){
  return ExtensionLoaderFactory.getExtensionLoader(Router.class,new ExtensionLoaderListener<Router>(){
    @Override public void onLoad(    ExtensionClass<Router> extensionClass){
      Class<? extends Router> implClass=extensionClass.getClazz();
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
          LOGGER.debug("Extension of interface " + Router.class + ", " + implClass + "(" + alias + ") will auto active");
        }
      }
    }
  }
);
}
