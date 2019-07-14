@Override public void read(Message message){
  ReadBuffer in=message.getContent().asReadBuffer();
  String senderId=message.getSenderId();
  Serializer serializer=graph.getDataSerializer();
  MgmtLogType logType=serializer.readObjectNotNull(in,MgmtLogType.class);
  Preconditions.checkNotNull(logType);
switch (logType) {
case CACHED_TYPE_EVICTION:
{
      long evictionId=VariableLong.readPositive(in);
      long numEvictions=VariableLong.readPositive(in);
      for (int i=0; i < numEvictions; i++) {
        long typeId=VariableLong.readPositive(in);
        schemaCache.expireSchemaElement(typeId);
      }
      final GraphCacheEvictionAction action=serializer.readObjectNotNull(in,GraphCacheEvictionAction.class);
      Preconditions.checkNotNull(action);
      final Thread ack=new Thread(new SendAckOnTxClose(evictionId,senderId,graph.getOpenTransactions(),action,graph.getGraphName()));
      ack.setDaemon(true);
      ack.start();
      break;
    }
case CACHED_TYPE_EVICTION_ACK:
{
    String receiverId=serializer.readObjectNotNull(in,String.class);
    long evictionId=VariableLong.readPositive(in);
    if (receiverId.equals(graph.getConfiguration().getUniqueGraphId())) {
      EvictionTrigger evictTrigger=evictionTriggerMap.get(evictionId);
      if (evictTrigger != null) {
        evictTrigger.receivedAcknowledgement(senderId);
      }
 else       log.error("Could not find eviction trigger for {} from {}",evictionId,senderId);
    }
    break;
  }
default :
assert logType == MgmtLogType.CONFIG_MUTATION;
break;
}
}
