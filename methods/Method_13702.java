/** 
 * @param messageType
 * @param queueName
 * @param messageListener
 * @return boolean
 */
private boolean startReceiveMsg(String messageType,String queueName,SmsMessageListener messageListener){
  String accessKeyId=aliCloudProperties.getAccessKey();
  String accessKeySecret=aliCloudProperties.getSecretKey();
  boolean result=true;
  try {
    new DefaultAlicomMessagePuller().startReceiveMsg(accessKeyId,accessKeySecret,messageType,queueName,messageListener);
    EndpointManager.addReceiveMessageEntity(new ReceiveMessageEntity(messageType,queueName,messageListener));
  }
 catch (  ClientException e) {
    log.error("start sms report message listener cause an exception",e);
    result=false;
  }
catch (  ParseException e) {
    log.error("start sms report message listener cause an exception",e);
    result=false;
  }
  return result;
}
