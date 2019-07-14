/** 
 * ?????
 * @param groupId groupId
 * @param locks    locks
 * @param lockType lockType
 * @return message
 */
public static MessageDto acquireLocks(String groupId,Set<String> locks,int lockType){
  DTXLockParams dtxLockParams=new DTXLockParams();
  dtxLockParams.setGroupId(groupId);
  dtxLockParams.setContextId(Transactions.APPLICATION_ID_WHEN_RUNNING);
  dtxLockParams.setLocks(locks);
  dtxLockParams.setLockType(lockType);
  MessageDto messageDto=new MessageDto();
  messageDto.setAction(MessageConstants.ACTION_ACQUIRE_DTX_LOCK);
  messageDto.setData(dtxLockParams);
  return messageDto;
}
