private String getenv(String name){
  final String env=System.getProperty(name);
  if (env != null) {
    return env;
  }
  final String getenv=System.getenv(name);
  return getenv;
}
