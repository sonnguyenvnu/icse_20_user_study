@JsonCreator public static DispatcherConfig create(@JsonProperty("HeartbeatPeriod") final Long heartbeatPeriod){
  return builder().heartbeatPeriod(heartbeatPeriod).build();
}
