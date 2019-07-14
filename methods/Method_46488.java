/** 
 * ????? ????  ?? / ?? ??
 * @param info info
 */
@Override public void postBusinessCode(TxTransactionInfo info){
  transactionControlTemplate.notifyGroup(info.getGroupId(),info.getUnitId(),info.getTransactionType(),DTXLocalContext.transactionState(globalContext.dtxState(info.getGroupId())));
}
