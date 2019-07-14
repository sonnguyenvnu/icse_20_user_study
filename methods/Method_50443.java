/** 
 * delete transaction by disruptor.
 * @param hmilyTransaction {@linkplain HmilyTransaction}
 */
public void deleteTransaction(final HmilyTransaction hmilyTransaction){
  hmilyTransactionEventPublisher.publishEvent(hmilyTransaction,EventTypeEnum.DELETE.getCode());
}
