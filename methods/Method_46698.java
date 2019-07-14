/** 
 * ????
 * @param action  action
 * @param message message
 * @return MessageDto
 */
public static MessageDto failResponse(Serializable message,String action){
  MessageDto messageDto=new MessageDto();
  messageDto.setAction(action);
  messageDto.setState(MessageConstants.STATE_EXCEPTION);
  messageDto.setData(message);
  return messageDto;
}
