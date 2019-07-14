/** 
 * Confirm.
 * @param hmilyTransaction the hmily transaction
 */
public void confirm(final HmilyTransaction hmilyTransaction){
  final List<HmilyParticipant> hmilyParticipants=hmilyTransaction.getHmilyParticipants();
  List<HmilyParticipant> failList=Lists.newArrayListWithCapacity(hmilyParticipants.size());
  boolean success=true;
  if (CollectionUtils.isNotEmpty(hmilyParticipants)) {
    for (    HmilyParticipant hmilyParticipant : hmilyParticipants) {
      try {
        HmilyReflector.executor(hmilyParticipant.getTransId(),HmilyActionEnum.CONFIRMING,hmilyParticipant.getConfirmHmilyInvocation());
      }
 catch (      Exception e) {
        LogUtil.error(LOGGER,"execute confirm exception:{}",() -> e);
        success=false;
        failList.add(hmilyParticipant);
      }
 finally {
        HmilyTransactionContextLocal.getInstance().remove();
      }
    }
    executeHandler(success,hmilyTransaction,failList);
  }
}
