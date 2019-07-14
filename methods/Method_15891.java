public static void register(String packages){
  register(tokenizeToStringArray(packages,ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
}
