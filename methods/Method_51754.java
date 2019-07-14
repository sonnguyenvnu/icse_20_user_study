public void setProperties(Properties properties){
  ignoreAnnotations=Boolean.parseBoolean(properties.getProperty(IGNORE_ANNOTATIONS,"false"));
  ignoreLiterals=Boolean.parseBoolean(properties.getProperty(IGNORE_LITERALS,"false"));
  ignoreIdentifiers=Boolean.parseBoolean(properties.getProperty(IGNORE_IDENTIFIERS,"false"));
}
