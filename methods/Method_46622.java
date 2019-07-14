/** 
 * ?????
 * @param notifyGroupParams notifyGroupParams
 * @return MessageDto
 */
public static MessageDto notifyGroup(NotifyGroupParams notifyGroupParams){
  MessageDto msg=new MessageDto();
  msg.setGroupId(notifyGroupParams.getGroupId());
  msg.setAction(MessageConstants.ACTION_NOTIFY_GROUP);
  msg.setData(notifyGroupParams);
  return msg;
}
