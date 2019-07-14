/** 
 * ????????
 * @param message message
 * @param action  action
 * @return MessageDto
 */
public static MessageDto notifyUnitOkResponse(Serializable message,String action){
  MessageDto messageDto=new MessageDto();
  messageDto.setAction(action);
  messageDto.setState(MessageConstants.STATE_OK);
  messageDto.setData(message);
  return messageDto;
}
