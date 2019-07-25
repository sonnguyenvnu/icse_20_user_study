@JsonCreator public static LogConfig create(@JsonProperty("Type") final String logType,@JsonProperty("Config") final Map<String,String> logOptions){
  final ImmutableMap<String,String> logOptionsCopy=logOptions == null ? null : ImmutableMap.copyOf(logOptions);
  return new AutoValue_LogConfig(logType,logOptionsCopy);
}
