/** 
 * @param smsUpMessageListener
 * @return boolean
 */
@Override public boolean startSmsUpMessageListener(SmsUpMessageListener smsUpMessageListener){
  String messageType="SmsUp";
  String queueName=smsProperties.getUpQueueName();
  return startReceiveMsg(messageType,queueName,smsUpMessageListener);
}
