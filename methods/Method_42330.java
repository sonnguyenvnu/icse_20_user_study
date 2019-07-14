/** 
 * ???????????????????????????.<br/>
 * @param rpOrderResultQueryVo
 * @throws Exception
 */
public void addToNotifyTaskDelayQueue(RpOrderResultQueryVo rpOrderResultQueryVo){
  if (rpOrderResultQueryVo == null) {
    return;
  }
  LOG.info("===>addToOrderQueryTaskDelayQueue bank order no:" + rpOrderResultQueryVo.getBankOrderNo());
  Integer notifyTimes=rpOrderResultQueryVo.getNotifyTimes();
  Integer maxNotifyTimes=rpOrderResultQueryVo.getLimitNotifyTimes();
  if (rpOrderResultQueryVo.getNotifyTimes().intValue() == 0) {
    rpOrderResultQueryVo.setLastNotifyTime(new Date());
  }
 else {
    rpOrderResultQueryVo.setLastNotifyTime(rpOrderResultQueryVo.getEditTime());
  }
  if (notifyTimes < maxNotifyTimes) {
    LOG.info("===>bank order No  " + rpOrderResultQueryVo.getBankOrderNo() + ", ??????lastNotifyTime:" + DateUtils.formatDate(rpOrderResultQueryVo.getLastNotifyTime(),"yyyy-MM-dd HH:mm:ss SSS"));
    AppOrderPollingApplication.tasks.put(new PollingTask(rpOrderResultQueryVo));
  }
}
