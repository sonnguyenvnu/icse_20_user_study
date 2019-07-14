public static Map<String,Object> getSmsEndpointMessage(){
  List<SendSmsRequest> sendSmsRequests=new LinkedList<>();
  List<SendBatchSmsRequest> sendBatchSmsRequests=new LinkedList<>();
  List<ReceiveMessageEntity> receiveMessageEntities=new LinkedList<>();
  try {
    SEND_REENTRANT_LOCK.lock();
    SEND_BATCH_REENTRANT_LOCK.lock();
    sendSmsRequests.addAll(SEND_SMS_REQUESTS);
    sendBatchSmsRequests.addAll(SEND_BATCH_SMS_REQUESTS);
  }
  finally {
    SEND_REENTRANT_LOCK.unlock();
    SEND_BATCH_REENTRANT_LOCK.unlock();
  }
  receiveMessageEntities.addAll(RECEIVE_MESSAGE_ENTITIES);
  Map<String,Object> endpointMessages=new HashMap<>();
  endpointMessages.put("send-sms-request",sendSmsRequests);
  endpointMessages.put("send-batch-sms-request",sendBatchSmsRequests);
  endpointMessages.put("message-listener",receiveMessageEntities);
  return endpointMessages;
}
