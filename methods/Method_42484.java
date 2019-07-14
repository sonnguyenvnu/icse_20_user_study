/** 
 * ??????
 * @param notifyUrl ????
 * @param merchantOrderNo ?????
 * @param merchantNo ????
 */
@Override public void notifySend(String notifyUrl,String merchantOrderNo,String merchantNo){
  RpNotifyRecord record=new RpNotifyRecord();
  record.setNotifyTimes(0);
  record.setLimitNotifyTimes(5);
  record.setStatus(NotifyStatusEnum.CREATED.name());
  record.setUrl(notifyUrl);
  record.setMerchantOrderNo(merchantOrderNo);
  record.setMerchantNo(merchantNo);
  record.setNotifyType(NotifyTypeEnum.MERCHANT.name());
  Object toJSON=JSONObject.toJSON(record);
  final String str=toJSON.toString();
  notifyJmsTemplate.setDefaultDestinationName(MqConfig.MERCHANT_NOTIFY_QUEUE);
  notifyJmsTemplate.send(new MessageCreator(){
    public Message createMessage(    Session session) throws JMSException {
      return session.createTextMessage(str);
    }
  }
);
}
