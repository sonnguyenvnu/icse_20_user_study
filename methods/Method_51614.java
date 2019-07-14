/** 
 * Load properties from one or more files or resources. <p> This method recursively finds property files or JAR resources matching {@matchstring}. </p> . <p> The method is intended to be able to use , so any
 * @param matchString
 * @return "current" set of properties (from one or more resources/propertyfiles)
 */
private Properties loadDBProperties(String matchString) throws IOException {
  LOGGER.entering(DBType.class.getCanonicalName(),matchString);
  ResourceBundle resourceBundle=null;
  if (LOGGER.isLoggable(Level.FINEST)) {
    LOGGER.finest("class_path+" + System.getProperty("java.class.path"));
  }
  File propertiesFile=new File(matchString);
  if (LOGGER.isLoggable(Level.FINEST)) {
    LOGGER.finest("Attempting File no file suffix: " + matchString);
  }
  try (InputStream stream=Files.newInputStream(propertiesFile.toPath())){
    resourceBundle=new PropertyResourceBundle(stream);
    propertiesSource=propertiesFile.getAbsolutePath();
    LOGGER.finest("FileSystemWithoutExtension");
  }
 catch (  NoSuchFileException notFoundOnFilesystemWithoutExtension) {
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("notFoundOnFilesystemWithoutExtension");
      LOGGER.finest("Attempting File with added file suffix: " + matchString + ".properties");
    }
    propertiesFile=new File(matchString + ".properties");
    try (InputStream stream=Files.newInputStream(propertiesFile.toPath())){
      resourceBundle=new PropertyResourceBundle(stream);
      propertiesSource=propertiesFile.getAbsolutePath();
      LOGGER.finest("FileSystemWithExtension");
    }
 catch (    NoSuchFileException notFoundOnFilesystemWithExtensionTackedOn) {
      if (LOGGER.isLoggable(Level.FINEST)) {
        LOGGER.finest("Attempting JARWithoutClassPrefix: " + matchString);
      }
      try {
        resourceBundle=ResourceBundle.getBundle(matchString);
        propertiesSource="[" + INTERNAL_SETTINGS + "]" + File.separator + matchString + ".properties";
        LOGGER.finest("InJarWithoutPath");
      }
 catch (      Exception notInJarWithoutPath) {
        if (LOGGER.isLoggable(Level.FINEST)) {
          LOGGER.finest("Attempting JARWithClass prefix: " + DBType.class.getCanonicalName() + "." + matchString);
        }
        try {
          resourceBundle=ResourceBundle.getBundle(DBType.class.getPackage().getName() + "." + matchString);
          propertiesSource="[" + INTERNAL_SETTINGS + "]" + File.separator + matchString + ".properties";
          LOGGER.finest("found InJarWithPath");
        }
 catch (        Exception notInJarWithPath) {
          notInJarWithPath.printStackTrace();
          notFoundOnFilesystemWithExtensionTackedOn.printStackTrace();
          throw new RuntimeException(" Could not locate DBTYpe settings : " + matchString,notInJarWithPath);
        }
      }
    }
  }
  Properties matchedProperties=getResourceBundleAsProperties(resourceBundle);
  resourceBundle=null;
  String saveLoadedFrom=getPropertiesSource();
  String extendedPropertyFile=(String)matchedProperties.remove("extends");
  if (null != extendedPropertyFile && !"".equals(extendedPropertyFile.trim())) {
    Properties extendedProperties=loadDBProperties(extendedPropertyFile.trim());
    extendedProperties.putAll(matchedProperties);
    matchedProperties=extendedProperties;
  }
  propertiesSource=saveLoadedFrom;
  setProperties(matchedProperties);
  return matchedProperties;
}
