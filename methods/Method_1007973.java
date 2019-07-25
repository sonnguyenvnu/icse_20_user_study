@JsonCreator static RemoteManager create(@JsonProperty("Addr") final String addr,@JsonProperty("NodeID") final String nodeId){
  return new AutoValue_RemoteManager(addr,nodeId);
}
