private static void addConfigFromProperties(Map<String,String> config,Map<String,?> properties){
  for (  String prop : properties.keySet()) {
    if (prop.startsWith("flyway.")) {
      config.put(prop,properties.get(prop).toString());
    }
  }
}
