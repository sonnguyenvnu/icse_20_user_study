/** 
 * Invokes provider to get a bean.
 */
protected Object invokeProvider(final ProviderDefinition provider){
  if (provider.method != null) {
    final Object bean;
    if (provider.beanName != null) {
      bean=getBean(provider.beanName);
    }
 else {
      bean=null;
    }
    try {
      return provider.method.invoke(bean);
    }
 catch (    Exception ex) {
      throw new PetiteException("Invalid provider method: " + provider.method.getName(),ex);
    }
  }
  throw new PetiteException("Invalid provider");
}
