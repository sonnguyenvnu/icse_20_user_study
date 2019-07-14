/** 
 * this is Participant transaction preTry.
 * @param context transaction context.
 * @param point   cut point
 * @return TccTransaction hmily transaction
 */
public HmilyTransaction preTryParticipant(final HmilyTransactionContext context,final ProceedingJoinPoint point){
  LogUtil.debug(LOGGER,"participant hmily transaction start..?{}",context::toString);
  final HmilyTransaction hmilyTransaction=buildHmilyTransaction(point,HmilyRoleEnum.PROVIDER.getCode(),context.getTransId());
  HmilyTransactionGuavaCacheManager.getInstance().cacheHmilyTransaction(hmilyTransaction);
  hmilyTransactionEventPublisher.publishEvent(hmilyTransaction,EventTypeEnum.SAVE.getCode());
  context.setRole(HmilyRoleEnum.LOCAL.getCode());
  HmilyTransactionContextLocal.getInstance().set(context);
  return hmilyTransaction;
}
