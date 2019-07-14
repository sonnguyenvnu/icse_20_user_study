/** 
 * ????
 * @param action  action
 * @param message message
 * @return MessageDto
 */
public static MessageDto okResponse(Serializable message,String action){
  MessageDto messageDto=new MessageDto();
  messageDto.setState(MessageConstants.STATE_OK);
  messageDto.setAction(action);
  messageDto.setData(message);
  return messageDto;
}
