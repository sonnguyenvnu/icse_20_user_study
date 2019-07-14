/** 
 * ????????
 * @param message message
 * @param action  action
 * @return MessageDto
 */
public static MessageDto notifyUnitFailResponse(Serializable message,String action){
  MessageDto messageDto=new MessageDto();
  messageDto.setState(MessageConstants.STATE_EXCEPTION);
  messageDto.setAction(action);
  messageDto.setData(message);
  return messageDto;
}
