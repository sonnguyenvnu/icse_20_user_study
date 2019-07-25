public void run(){
  try {
    if (eventsToSend != null && !eventsToSend.isEmpty()) {
      ProvenanceEventRecordDTOHolder eventRecordDTOHolder=new ProvenanceEventRecordDTOHolder();
      eventRecordDTOHolder.setEvents(eventsToSend);
      getProvenanceEventActiveMqWriter().writeBatchEvents(eventRecordDTOHolder);
    }
    if (statsToSend != null && !statsToSend.isEmpty()) {
      AggregatedFeedProcessorStatisticsHolderV3 statsHolder=new AggregatedFeedProcessorStatisticsHolderV3();
      statsHolder.setProcessorIdRunningFlows(processorIdRunningFlows);
      statsHolder.setCollectionId(statsToSend.get(0).getCollectionId());
      statsHolder.setFeedStatistics(statsToSend);
      getProvenanceEventActiveMqWriter().writeStats(statsHolder);
    }
    if (runningFlowsChanged && eventsToSend == null && statsToSend == null) {
      log.info("Sending Running Flow counts statistics for feeds to JMS");
      AggregatedFeedProcessorStatisticsHolderV3 statsHolder=new AggregatedFeedProcessorStatisticsHolderV3();
      statsHolder.setProcessorIdRunningFlows(processorIdRunningFlows);
      statsHolder.setCollectionId(UUID.randomUUID().toString());
      statsHolder.setFeedStatistics(statsToSend);
      getProvenanceEventActiveMqWriter().writeStats(statsHolder);
    }
    getRemoteProvenanceEventJmsListener().notifyOtherNodesAboutRemoteInputPortSendEvents();
  }
 catch (  Exception e) {
    log.error("Error writing provenance events to JMS",e);
  }
}
