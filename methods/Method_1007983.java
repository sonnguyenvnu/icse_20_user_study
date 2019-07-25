@JsonCreator public static UpdateConfig create(@JsonProperty("Parallelism") final Long parallelism,@JsonProperty("Delay") final Long delay,@JsonProperty("FailureAction") final String failureAction){
  return new AutoValue_UpdateConfig(parallelism,delay,failureAction);
}
