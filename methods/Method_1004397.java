private Map<String,MessageException> process(BrokerGroupInfo target,Datagram response) throws RemoteResponseUnreadableException {
  ByteBuf buf=response.getBody();
  try {
    if (buf == null || !buf.isReadable()) {
      return Collections.emptyMap();
    }
    Map<String,SendResult> resultMap=QMQSerializer.deserializeSendResultMap(buf);
    boolean brokerReject=false;
    Map<String,MessageException> map=Maps.newHashMapWithExpectedSize(resultMap.size());
    for (    Map.Entry<String,SendResult> entry : resultMap.entrySet()) {
      String messageId=entry.getKey();
      SendResult result=entry.getValue();
switch (result.getCode()) {
case MessageProducerCode.SUCCESS:
        break;
case MessageProducerCode.MESSAGE_DUPLICATE:
      map.put(messageId,new DuplicateMessageException(messageId));
    break;
case MessageProducerCode.BROKER_BUSY:
  map.put(messageId,new MessageException(messageId,MessageException.BROKER_BUSY));
break;
case MessageProducerCode.BROKER_READ_ONLY:
brokerReject=true;
map.put(messageId,new BrokerRejectException(messageId));
break;
case MessageProducerCode.SUBJECT_NOT_ASSIGNED:
map.put(messageId,new SubjectNotAssignedException(messageId));
break;
case MessageProducerCode.BLOCK:
map.put(messageId,new BlockMessageException(messageId));
break;
default :
map.put(messageId,new MessageException(messageId,result.getRemark()));
break;
}
}
if (brokerReject) {
handleSendReject(target);
}
return map;
}
  finally {
response.release();
}
}
