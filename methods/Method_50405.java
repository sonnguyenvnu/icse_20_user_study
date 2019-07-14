/** 
 * Convert byte [ ].
 * @param hmilyTransaction   the tcc transaction
 * @param objectSerializer the object serializer
 * @return the byte [ ]
 * @throws HmilyException the tcc exception
 */
public static byte[] convert(final HmilyTransaction hmilyTransaction,final ObjectSerializer objectSerializer) throws HmilyException {
  CoordinatorRepositoryAdapter adapter=new CoordinatorRepositoryAdapter();
  adapter.setTransId(hmilyTransaction.getTransId());
  adapter.setLastTime(hmilyTransaction.getLastTime());
  adapter.setCreateTime(hmilyTransaction.getCreateTime());
  adapter.setRetriedCount(hmilyTransaction.getRetriedCount());
  adapter.setStatus(hmilyTransaction.getStatus());
  adapter.setTargetClass(hmilyTransaction.getTargetClass());
  adapter.setTargetMethod(hmilyTransaction.getTargetMethod());
  adapter.setPattern(hmilyTransaction.getPattern());
  adapter.setRole(hmilyTransaction.getRole());
  adapter.setVersion(hmilyTransaction.getVersion());
  if (CollectionUtils.isNotEmpty(hmilyTransaction.getHmilyParticipants())) {
    final HmilyParticipant hmilyParticipant=hmilyTransaction.getHmilyParticipants().get(0);
    adapter.setConfirmMethod(hmilyParticipant.getConfirmHmilyInvocation().getMethodName());
    adapter.setCancelMethod(hmilyParticipant.getCancelHmilyInvocation().getMethodName());
  }
  adapter.setContents(objectSerializer.serialize(hmilyTransaction.getHmilyParticipants()));
  return objectSerializer.serialize(adapter);
}
