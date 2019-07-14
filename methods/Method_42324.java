@Override public void onMessage(Message message){
  try {
    ActiveMQTextMessage msg=(ActiveMQTextMessage)message;
    final String ms=msg.getText();
    log.info("== receive message:" + ms);
    JSON json=(JSON)JSONObject.parse(ms);
    RpNotifyRecord notifyRecord=JSONObject.toJavaObject(json,RpNotifyRecord.class);
    if (notifyRecord == null) {
      return;
    }
    notifyRecord.setStatus(NotifyStatusEnum.CREATED.name());
    notifyRecord.setCreateTime(new Date());
    notifyRecord.setLastNotifyTime(new Date());
    if (!StringUtil.isEmpty(notifyRecord.getId())) {
      RpNotifyRecord notifyRecordById=rpNotifyService.getNotifyRecordById(notifyRecord.getId());
      if (notifyRecordById != null) {
        return;
      }
    }
    while (rpNotifyService == null) {
      Thread.currentThread().sleep(1000);
    }
    try {
      notifyPersist.saveNotifyRecord(notifyRecord);
      notifyRecord=rpNotifyService.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(notifyRecord.getMerchantNo(),notifyRecord.getMerchantOrderNo(),notifyRecord.getNotifyType());
      notifyQueue.addElementToList(notifyRecord);
    }
 catch (    BizException e) {
      log.error("BizException :",e);
    }
catch (    Exception e) {
      log.error(e);
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
    log.error(e);
  }
}
