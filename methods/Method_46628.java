/** 
 * ???????
 * @param txExceptionParams txExceptionParams
 * @return MessageDto
 */
public static MessageDto writeTxException(TxExceptionParams txExceptionParams){
  MessageDto messageDto=new MessageDto();
  messageDto.setAction(MessageConstants.ACTION_WRITE_EXCEPTION);
  messageDto.setGroupId(txExceptionParams.getGroupId());
  messageDto.setData(txExceptionParams);
  return messageDto;
}
