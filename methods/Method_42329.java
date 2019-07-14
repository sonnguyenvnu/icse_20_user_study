/** 
 * ??????
 * @param rpOrderResultQueryVo
 */
public void getOrderResult(RpOrderResultQueryVo rpOrderResultQueryVo){
  Integer notifyTimes=rpOrderResultQueryVo.getNotifyTimes();
  Integer maxNotifyTimes=rpOrderResultQueryVo.getLimitNotifyTimes();
  Date notifyTime=new Date();
  rpOrderResultQueryVo.setEditTime(notifyTime);
  rpOrderResultQueryVo.setNotifyTimes(notifyTimes + 1);
  LOG.info("notifyTimes:{}  , maxNotifyTimes:{} ",notifyTimes,maxNotifyTimes);
  try {
    boolean processingResult=rpTradePaymentManagerService.processingTradeRecord(rpOrderResultQueryVo.getBankOrderNo());
    LOG.info("order processing result:{}",processingResult);
    if (!processingResult) {
      if (rpOrderResultQueryVo.getNotifyTimes() < maxNotifyTimes) {
        pollingQueue.addToNotifyTaskDelayQueue(rpOrderResultQueryVo);
        LOG.info("===>bank order {} need processing again ",rpOrderResultQueryVo.getBankOrderNo());
      }
 else {
        LOG.info("bank order No {} not pay",rpOrderResultQueryVo.getBankOrderNo());
      }
    }
  }
 catch (  BizException e) {
    LOG.error("????????:",e);
    if (rpOrderResultQueryVo.getNotifyTimes() < maxNotifyTimes) {
      pollingQueue.addToNotifyTaskDelayQueue(rpOrderResultQueryVo);
      LOG.info("===>bank order {} need processing again ",rpOrderResultQueryVo.getBankOrderNo());
    }
 else {
      LOG.info("bank order No {} not pay",rpOrderResultQueryVo.getBankOrderNo());
    }
  }
catch (  Exception e) {
    LOG.error("????????:",e);
    if (rpOrderResultQueryVo.getNotifyTimes() < maxNotifyTimes) {
      pollingQueue.addToNotifyTaskDelayQueue(rpOrderResultQueryVo);
      LOG.info("===>bank order {} need processing again ",rpOrderResultQueryVo.getBankOrderNo());
    }
 else {
      LOG.info("bank order No {} not pay",rpOrderResultQueryVo.getBankOrderNo());
    }
  }
}
