@SuppressWarnings({"rawtypes","unchecked"}) @Activate public void activate(){
  Configuration configuration;
  try {
    configuration=configurationAdmin.getConfiguration(Constants.JAXRS_CONNECTOR_CONFIG,null);
    if (configuration != null) {
      Dictionary properties=configuration.getProperties();
      if (properties == null) {
        properties=new Properties();
      }
      String rootAlias=(String)properties.get(Constants.JAXRS_CONNECTOR_ROOT_PROPERTY);
      if (!RESTConstants.REST_URI.equals(rootAlias)) {
        properties.put(Constants.JAXRS_CONNECTOR_ROOT_PROPERTY,RESTConstants.REST_URI);
        configuration.update(properties);
      }
    }
  }
 catch (  IOException e) {
    logger.error("Could not set REST configuration properties!",e);
  }
}
