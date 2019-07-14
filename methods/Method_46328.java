protected void registerProvider(ResteasyProviderFactory providerFactory){
  Set<Class> internalProviderClasses=JAXRSProviderManager.getInternalProviderClasses();
  if (CommonUtils.isNotEmpty(internalProviderClasses)) {
    for (    Class providerClass : internalProviderClasses) {
      providerFactory.register(providerClass);
    }
  }
  Map<String,String> parameters=serverConfig.getParameters();
  if (CommonUtils.isNotEmpty(parameters)) {
    String crossDomainStr=parameters.get(RpcConstants.ALLOWED_ORIGINS);
    if (StringUtils.isNotBlank(crossDomainStr)) {
      final CorsFilter corsFilter=new CorsFilter();
      String[] domains=StringUtils.splitWithCommaOrSemicolon(crossDomainStr);
      for (      String allowDomain : domains) {
        corsFilter.getAllowedOrigins().add(allowDomain);
      }
      JAXRSProviderManager.registerCustomProviderInstance(corsFilter);
    }
  }
  Set<Object> customProviderInstances=JAXRSProviderManager.getCustomProviderInstances();
  if (CommonUtils.isNotEmpty(customProviderInstances)) {
    for (    Object provider : customProviderInstances) {
      PropertyInjector propertyInjector=providerFactory.getInjectorFactory().createPropertyInjector(JAXRSProviderManager.getTargetClass(provider),providerFactory);
      propertyInjector.inject(provider);
      providerFactory.registerProviderInstance(provider);
    }
  }
  if (LOGGER.isDebugEnabled()) {
    Set pcs=providerFactory.getProviderClasses();
    StringBuilder sb=new StringBuilder();
    sb.append("\ndefault-providers:\n");
    for (    Object provider : pcs) {
      sb.append("  ").append(provider).append("\n");
    }
    LOGGER.debug(sb.toString());
  }
}
