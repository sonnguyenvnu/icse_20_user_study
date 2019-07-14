@Override public void onMessage(Message message){
  try {
    ActiveMQTextMessage msg=(ActiveMQTextMessage)message;
    final String msgText=msg.getText();
    log.info("== receive bankOrderNo :" + msgText);
    RpOrderResultQueryVo rpOrderResultQueryVo=new RpOrderResultQueryVo();
    rpOrderResultQueryVo.setBankOrderNo(msgText);
    rpOrderResultQueryVo.setStatus(NotifyStatusEnum.CREATED.name());
    rpOrderResultQueryVo.setCreateTime(new Date());
    rpOrderResultQueryVo.setEditTime(new Date());
    rpOrderResultQueryVo.setLastNotifyTime(new Date());
    rpOrderResultQueryVo.setNotifyTimes(0);
    rpOrderResultQueryVo.setLimitNotifyTimes(pollingParam.getMaxNotifyTimes());
    Map<Integer,Integer> notifyParams=pollingParam.getNotifyParams();
    rpOrderResultQueryVo.setNotifyRule(JSONObject.toJSONString(notifyParams));
    try {
      pollingQueue.addToNotifyTaskDelayQueue(rpOrderResultQueryVo);
    }
 catch (    BizException e) {
      log.error("BizException :",e);
    }
catch (    Exception e) {
      log.error(e);
    }
  }
 catch (  Exception e) {
    log.error(e);
  }
}
