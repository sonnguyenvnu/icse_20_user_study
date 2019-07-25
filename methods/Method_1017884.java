/** 
 * Called when a node (including this node) receives a message
 * @param msg a message
 */
public void receive(Message msg){
  ClusterMessage clusterMessage=(ClusterMessage)msg.getObject();
  String from=msg.getSrc().toString();
  Set<ClusterServiceMessageReceiver> receivers=messageReceivers.entrySet().stream().filter(e -> ALL_TOPIC.equalsIgnoreCase(e.getKey()) || e.getKey().equalsIgnoreCase(clusterMessage.getType())).flatMap(e -> e.getValue().stream()).collect(Collectors.toSet());
  log.info("Receiving message from {} of type: {}, notifying {} receivers ",msg.getSrc(),clusterMessage.getType(),receivers.size());
  receivers.stream().forEach(messageReceiver -> {
    try {
      messageReceiver.onMessageReceived(from,clusterMessage);
    }
 catch (    Exception e) {
      log.error("Error procesing onMessageReceived from {} topic: {}, message: {} ",from,clusterMessage.getType(),clusterMessage.getMessage(),e);
    }
  }
);
  clusterNodeSummary.messageReceived(clusterMessage.getType());
  acknowledgeMessage(from,clusterMessage);
}
