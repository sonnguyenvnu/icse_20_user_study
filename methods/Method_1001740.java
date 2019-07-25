public static String getenv(String var){
  final String env=System.getProperty(var);
  if (StringUtils.isNotEmpty(env)) {
    return StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(env);
  }
  final String getenv=System.getenv(var);
  if (StringUtils.isNotEmpty(getenv)) {
    return StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(getenv);
  }
  return null;
}
