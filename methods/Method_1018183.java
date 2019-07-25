public void update(NifiFlowCacheSnapshot syncSnapshot){
  processorIdToFeedNameMap.putAll(syncSnapshot.getProcessorIdToFeedNameMap());
  processorIdToFeedProcessGroupId.putAll(syncSnapshot.getProcessorIdToFeedProcessGroupId());
  processorIdToProcessorName.putAll(syncSnapshot.getProcessorIdToProcessorName());
  allStreamingFeeds=new HashSet<>(syncSnapshot.getAllStreamingFeeds());
  allFeeds.addAll(syncSnapshot.getAllFeeds());
  snapshotDate=syncSnapshot.getSnapshotDate();
  connectionIdToConnection.putAll(syncSnapshot.getConnectionIdToConnection());
  connectionIdToConnectionName.putAll(syncSnapshot.getConnectionIdToConnectionName());
  reusableTemplateProcessorIds.addAll(syncSnapshot.getReusableTemplateProcessorIds());
  feedToInputProcessorIds.putAll(syncSnapshot.getFeedToInputProcessorIds());
  inputProcessorRelations.putAll(syncSnapshot.getInputProcessorRelations());
  connectionSourceToDestination.putAll(syncSnapshot.getConnectionSourceToDestination());
}
