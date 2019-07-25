@JsonCreator static RaftConfig create(@JsonProperty("SnapshotInterval") final Integer snapshotInterval,@JsonProperty("KeepOldSnapshots") final Integer keepOldSnapshots,@JsonProperty("LogEntriesForSlowFollowers") final Integer logEntriesForSlowFollowers,@JsonProperty("ElectionTick") final Integer electionTick,@JsonProperty("HeartbeatTick") final Integer heartbeatTick){
  return builder().snapshotInterval(snapshotInterval).keepOldSnapshots(keepOldSnapshots).logEntriesForSlowFollowers(logEntriesForSlowFollowers).electionTick(electionTick).heartbeatTick(heartbeatTick).build();
}
