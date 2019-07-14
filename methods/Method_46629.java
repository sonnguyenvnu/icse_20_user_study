/** 
 * ????????
 * @param appName   appName
 * @param labelName labelName
 * @return MessageDto
 */
public static MessageDto initClient(String appName,String labelName){
  InitClientParams initClientParams=new InitClientParams();
  initClientParams.setAppName(appName);
  initClientParams.setLabelName(labelName);
  MessageDto messageDto=new MessageDto();
  messageDto.setData(initClientParams);
  messageDto.setAction(MessageConstants.ACTION_INIT_CLIENT);
  return messageDto;
}
