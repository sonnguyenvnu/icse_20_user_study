/** 
 * Transform bean tcc transaction.
 * @param contents         the contents
 * @param objectSerializer the object serializer
 * @return the tcc transaction
 * @throws HmilyException the tcc exception
 */
@SuppressWarnings("unchecked") public static HmilyTransaction transformBean(final byte[] contents,final ObjectSerializer objectSerializer) throws HmilyException {
  HmilyTransaction hmilyTransaction=new HmilyTransaction();
  final CoordinatorRepositoryAdapter adapter=objectSerializer.deSerialize(contents,CoordinatorRepositoryAdapter.class);
  List<HmilyParticipant> hmilyParticipants=objectSerializer.deSerialize(adapter.getContents(),ArrayList.class);
  hmilyTransaction.setLastTime(adapter.getLastTime());
  hmilyTransaction.setRetriedCount(adapter.getRetriedCount());
  hmilyTransaction.setCreateTime(adapter.getCreateTime());
  hmilyTransaction.setTransId(adapter.getTransId());
  hmilyTransaction.setStatus(adapter.getStatus());
  hmilyTransaction.setHmilyParticipants(hmilyParticipants);
  hmilyTransaction.setRole(adapter.getRole());
  hmilyTransaction.setPattern(adapter.getPattern());
  hmilyTransaction.setTargetClass(adapter.getTargetClass());
  hmilyTransaction.setTargetMethod(adapter.getTargetMethod());
  hmilyTransaction.setVersion(adapter.getVersion());
  return hmilyTransaction;
}
