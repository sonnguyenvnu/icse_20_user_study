/** 
 * ??????
 * @param messageDto ????
 * @return  ????
 */
public static boolean statusOk(MessageDto messageDto){
  return messageDto.getState() == MessageConstants.STATE_OK;
}
