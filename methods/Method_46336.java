/** 
 * ??jaxrs Provider
 * @return SofaResteasyClientBuilder
 */
public SofaResteasyClientBuilder registerProvider(){
  ResteasyProviderFactory providerFactory=getProviderFactory();
  Set<Class> internalProviderClasses=JAXRSProviderManager.getInternalProviderClasses();
  if (CommonUtils.isNotEmpty(internalProviderClasses)) {
    for (    Class providerClass : internalProviderClasses) {
      providerFactory.register(providerClass);
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
  return this;
}
