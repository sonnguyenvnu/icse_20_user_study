public void setProperties(Properties properties){
  caseSensitive=Boolean.parseBoolean(properties.getProperty(CASE_SENSITIVE,"false"));
}
