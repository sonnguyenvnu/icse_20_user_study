@JsonCreator static ServiceMode create(@JsonProperty("Replicated") final ReplicatedService replicated,@JsonProperty("Global") final GlobalService global){
  return builder().replicated(replicated).global(global).build();
}
