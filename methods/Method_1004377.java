void destroy(){
  tryCleanAck();
  for (  AckSendQueue sendQueue : senderMap.values()) {
    sendQueue.destroy(destroyWaitInSeconds * 1000L);
  }
}
