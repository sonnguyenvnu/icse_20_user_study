public void load(RemoteMessageResponseWithRelatedFlowFiles response){
  RemoteEventMessageResponse remoteEventMessageResponse=response.getRemoteEventMessageResponse();
  log.debug("KYLO-DEBUG: Loading remote events into feed statistics feed flowfile: {}, source flow file: {}, feed processor: {}, startTime: {}, feed flow running count: {}, tracking details:{}  ",remoteEventMessageResponse.getFeedFlowFileId(),remoteEventMessageResponse.getSourceFlowFileId(),remoteEventMessageResponse.getFeedProcessorId(),remoteEventMessageResponse.getFeedFlowFileStartTime(),remoteEventMessageResponse.getFeedFlowRunningCount(),remoteEventMessageResponse.isTrackingDetails());
  if (remoteEventMessageResponse.isTrackingDetails()) {
    this.detailedTrackingFeedFlowFileId.add(remoteEventMessageResponse.getFeedFlowFileId());
  }
  this.feedFlowFileIdToFeedProcessorId.put(remoteEventMessageResponse.getFeedFlowFileId(),remoteEventMessageResponse.getFeedProcessorId());
  this.allFlowFileToFeedFlowFile.put(remoteEventMessageResponse.getFeedFlowFileId(),remoteEventMessageResponse.getFeedFlowFileId());
  this.allFlowFileToFeedFlowFile.put(remoteEventMessageResponse.getSourceFlowFileId(),remoteEventMessageResponse.getFeedFlowFileId());
  this.feedFlowFileStartTime.putIfAbsent(remoteEventMessageResponse.getFeedFlowFileId(),remoteEventMessageResponse.getFeedFlowFileStartTime());
  this.feedFlowProcessing.putIfAbsent(remoteEventMessageResponse.getFeedFlowFileId(),new AtomicInteger(0));
  response.getRelatedFlowFiles().stream().forEach(ff -> allFlowFileToFeedFlowFile.putIfAbsent(ff,remoteEventMessageResponse.getFeedFlowFileId()));
}
