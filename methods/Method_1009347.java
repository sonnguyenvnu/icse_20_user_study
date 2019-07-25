/** 
 * Builds and returns all property mappings defined in the  {@code propertyMap}.
 */
Collection<MappingImpl> build(PropertyMap<S,D> propertyMap){
  try {
    PROPERTY_MAP_CONFIGURE.invoke(propertyMap,this);
    saveLastMapping();
  }
 catch (  IllegalAccessException e) {
    errors.errorAccessingConfigure(e);
  }
catch (  InvocationTargetException e) {
    Throwable cause=e.getCause();
    if (cause instanceof ConfigurationException)     throw (ConfigurationException)cause;
 else     errors.addMessage(cause,"Failed to configure mappings");
  }
catch (  NullPointerException e) {
    if (proxyErrors.hasErrors()) {
      throw proxyErrors.toException();
    }
    throw e;
  }
  errors.throwConfigurationExceptionIfErrorsExist();
  return propertyMappings;
}
