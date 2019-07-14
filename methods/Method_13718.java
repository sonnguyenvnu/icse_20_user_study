@Override protected synchronized Object doReceive(){
  if (messageQueueChooser.getMessageQueues() == null || messageQueueChooser.getMessageQueues().size() == 0) {
    return null;
  }
  try {
    int count=0;
    while (count < messageQueueChooser.getMessageQueues().size()) {
      MessageQueue messageQueue;
synchronized (this.consumerMonitor) {
        messageQueue=messageQueueChooser.choose();
        messageQueueChooser.increment();
      }
      long offset=consumer.fetchConsumeOffset(messageQueue,rocketMQConsumerProperties.getExtension().isFromStore());
      log.debug("topic='{}', group='{}', messageQueue='{}', offset now='{}'",this.topic,this.group,messageQueue,offset);
      PullResult pullResult;
      if (messageSelector != null) {
        pullResult=consumer.pull(messageQueue,messageSelector,offset,1);
      }
 else {
        pullResult=consumer.pull(messageQueue,(String)null,offset,1);
      }
      if (pullResult.getPullStatus() == PullStatus.FOUND) {
        List<MessageExt> messageExtList=pullResult.getMsgFoundList();
        Message message=RocketMQUtil.convertToSpringMessage(messageExtList.get(0));
        AcknowledgmentCallback ackCallback=this.ackCallbackFactory.createCallback(new RocketMQAckInfo(messageQueue,pullResult,consumer,offset));
        Message messageResult=MessageBuilder.fromMessage(message).setHeader(IntegrationMessageHeaderAccessor.ACKNOWLEDGMENT_CALLBACK,ackCallback).build();
        return messageResult;
      }
 else {
        log.debug("messageQueue='{}' PullResult='{}' with topic `{}`",messageQueueChooser.getMessageQueues(),pullResult.getPullStatus(),topic);
      }
      count++;
    }
  }
 catch (  Exception e) {
    log.error("Consumer pull error: " + e.getMessage(),e);
  }
  return null;
}
