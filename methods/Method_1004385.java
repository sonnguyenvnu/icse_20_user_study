public synchronized boolean offline(){
  isOnline=false;
  for (  PullEntry pullEntry : pullEntryMap.values()) {
    pullEntry.offline(HEALTHCHECKER);
  }
  for (  DefaultPullConsumer pullConsumer : pullConsumerMap.values()) {
    pullConsumer.offline(HEALTHCHECKER);
  }
  ackService.tryCleanAck();
  return true;
}
