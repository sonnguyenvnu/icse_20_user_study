/** 
 * Load properties into the given sofa.versions.properties resource.
 * @param resourceLocation the resource locations to load
 */
private Properties loadProperties(Resource resourceLocation){
  Assert.notNull(resourceLocation,"Properties resource location must not be null.");
  logger.info("Loading properties file from {}",resourceLocation);
  Properties result=new Properties();
  try {
    PropertiesLoaderUtils.fillProperties(result,new EncodedResource(resourceLocation));
  }
 catch (  IOException ex) {
    logger.warn("Error occurred when loading properties from {}: {}",resourceLocation,ex.getMessage());
  }
  return result;
}
