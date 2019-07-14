public void setProperties(Properties properties){
  ignoreIdentifiers=Boolean.parseBoolean(properties.getProperty(IGNORE_IDENTIFIERS,"false"));
  ignoreLiterals=Boolean.parseBoolean(properties.getProperty(IGNORE_LITERALS,"false"));
}
