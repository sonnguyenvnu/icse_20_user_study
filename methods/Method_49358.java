public static Status validate(String filename) throws IOException {
  try (final FileInputStream fis=new FileInputStream(filename)){
    new Properties().load(fis);
  }
   final PropertiesConfiguration apc;
  try {
    apc=new PropertiesConfiguration(filename);
  }
 catch (  ConfigurationException e) {
    throw new IOException(e);
  }
  Iterator<String> iterator=apc.getKeys();
  int totalKeys=0;
  int keysVerified=0;
  while (iterator.hasNext()) {
    totalKeys++;
    String key=iterator.next();
    String value=apc.getString(key);
    try {
      ConfigElement.PathIdentifier pid=ConfigElement.parse(GraphDatabaseConfiguration.ROOT_NS,key);
      Preconditions.checkNotNull(pid);
      Preconditions.checkNotNull(pid.element);
      if (!pid.element.isOption()) {
        log.warn("Config key {} is a namespace (only options can be keys)",key);
        continue;
      }
      final ConfigOption<?> opt;
      try {
        opt=(ConfigOption<?>)pid.element;
      }
 catch (      RuntimeException re) {
        log.warn("Config key {} maps to the element {}, but it could not be cast to an option",key,pid.element,re);
        continue;
      }
      try {
        Object o=new CommonsConfiguration(apc).get(key,opt.getDatatype());
        opt.verify(o);
        keysVerified++;
      }
 catch (      RuntimeException re) {
        log.warn("Config key {} is recognized, but its value {} could not be validated",key,value);
        log.debug("Validation exception on {}={} follows",key,value,re);
      }
    }
 catch (    RuntimeException re) {
      log.warn("Unknown config key {}",key);
    }
  }
  return new Status(totalKeys,totalKeys - keysVerified);
}
