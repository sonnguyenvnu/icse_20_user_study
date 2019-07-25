@JsonCreator static TaskDefaults create(@JsonProperty("LogDriver") final Driver logDriver){
  return builder().logDriver(logDriver).build();
}
