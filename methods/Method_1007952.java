public static LogConfig create(final String logType){
  return new AutoValue_LogConfig(logType,ImmutableMap.<String,String>builder().build());
}
