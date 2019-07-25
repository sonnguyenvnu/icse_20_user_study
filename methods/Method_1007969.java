@JsonCreator static OrchestrationConfig create(@JsonProperty("TaskHistoryRetentionLimit") final Integer taskHistoryRetentionLimit){
  return builder().taskHistoryRetentionLimit(taskHistoryRetentionLimit).build();
}
