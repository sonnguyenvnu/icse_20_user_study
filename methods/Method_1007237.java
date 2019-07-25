public void unsubscribe(List<String> topics,MQTTConnection mqttConnection,int messageId){
  final String clientID=mqttConnection.getClientId();
  for (  String t : topics) {
    Topic topic=new Topic(t);
    boolean validTopic=topic.isValid();
    if (!validTopic) {
      mqttConnection.dropConnection();
      LOG.warn("Topic filter is not valid. CId={}, topics: {}, offending topic filter: {}",clientID,topics,topic);
      return;
    }
    LOG.trace("Removing subscription. CId={}, topic={}",clientID,topic);
    subscriptions.removeSubscription(topic,clientID);
    String username=NettyUtils.userName(mqttConnection.channel);
    interceptor.notifyTopicUnsubscribed(topic.toString(),clientID,username);
  }
  mqttConnection.sendUnsubAckMessage(topics,clientID,messageId);
}
