private static void addConfigFromProperties(Map<String,String> config,Properties properties){
  for (  String prop : properties.stringPropertyNames()) {
    if (prop.startsWith("flyway.")) {
      config.put(prop,properties.getProperty(prop));
    }
  }
}
