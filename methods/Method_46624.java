/** 
 * ?????
 * @param locks locks
 * @return message
 */
public static MessageDto releaseLocks(Set<String> locks){
  DTXLockParams dtxLockParams=new DTXLockParams();
  dtxLockParams.setContextId(Transactions.APPLICATION_ID_WHEN_RUNNING);
  dtxLockParams.setLocks(locks);
  MessageDto messageDto=new MessageDto();
  messageDto.setAction(MessageConstants.ACTION_RELEASE_DTX_LOCK);
  messageDto.setData(dtxLockParams);
  return messageDto;
}
