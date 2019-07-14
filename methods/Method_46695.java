/** 
 * ??TxClient??
 * @param notifyConnectParams notifyConnectParams
 * @return MessageDto
 */
public static MessageDto newTxManager(NotifyConnectParams notifyConnectParams){
  MessageDto msg=new MessageDto();
  msg.setAction(MessageConstants.ACTION_NEW_TXMANAGER);
  msg.setData(notifyConnectParams);
  return msg;
}
