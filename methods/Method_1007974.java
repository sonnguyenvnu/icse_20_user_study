@JsonCreator static ReplicatedService create(@JsonProperty("Replicas") final Long replicas){
  return builder().replicas(replicas).build();
}
