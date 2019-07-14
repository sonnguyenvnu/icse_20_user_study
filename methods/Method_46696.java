/** 
 * ?????
 * @param notifyUnitParams notifyUnitParams
 * @return MessageDto
 */
public static MessageDto notifyUnit(NotifyUnitParams notifyUnitParams){
  MessageDto msg=new MessageDto();
  msg.setGroupId(notifyUnitParams.getGroupId());
  msg.setAction(MessageConstants.ACTION_NOTIFY_UNIT);
  msg.setData(notifyUnitParams);
  return msg;
}
