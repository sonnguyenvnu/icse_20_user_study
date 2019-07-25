public static String getenv(String name){
  final String env=System.getProperty(name);
  if (StringUtils.isNotEmpty(env)) {
    return env;
  }
  return System.getenv(name);
}
