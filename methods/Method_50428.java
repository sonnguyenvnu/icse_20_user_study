/** 
 * publish disruptor event.
 * @param hmilyTransaction {@linkplain HmilyTransaction }
 * @param type             {@linkplain EventTypeEnum}
 */
public void publishEvent(final HmilyTransaction hmilyTransaction,final int type){
  HmilyTransactionEvent event=new HmilyTransactionEvent();
  event.setType(type);
  event.setHmilyTransaction(hmilyTransaction);
  disruptorProviderManage.getProvider().onData(event);
}
