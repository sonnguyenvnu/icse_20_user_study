/** 
 * ?????
 * @param action action
 * @return MessageDto
 */
public static MessageDto serverException(String action){
  MessageDto messageDto=new MessageDto();
  messageDto.setAction(action);
  messageDto.setState(MessageConstants.STATE_EXCEPTION);
  return messageDto;
}
