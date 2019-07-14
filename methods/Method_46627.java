/** 
 * ????????
 * @param groupId groupId
 * @param unitId  unitId
 * @return MessageDto
 */
public static MessageDto askTransactionState(String groupId,String unitId){
  MessageDto messageDto=new MessageDto();
  messageDto.setGroupId(groupId);
  messageDto.setAction(MessageConstants.ACTION_ASK_TRANSACTION_STATE);
  messageDto.setData(new AskTransactionStateParams(groupId,unitId));
  return messageDto;
}
