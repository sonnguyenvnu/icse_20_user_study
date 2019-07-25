@Override public void initialise(final Properties properties){
  if (properties != null) {
    useJavaSerialisation=Boolean.parseBoolean(properties.getProperty(JAVA_SERIALISATION_CACHE));
  }
  if (properties != null && Boolean.parseBoolean(properties.getProperty(STATIC_CACHE))) {
    caches=STATIC_CACHES;
  }
 else {
    caches=nonStaticCaches;
  }
}
