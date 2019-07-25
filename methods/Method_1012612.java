/** 
 * ????????????
 * @param msg
 */
public void add(MessageProtobuf.Msg msg){
  if (msg == null || msg.getHead() == null) {
    return;
  }
  int handshakeMsgType=-1;
  int heartbeatMsgType=-1;
  int clientReceivedReportMsgType=imsClient.getClientReceivedReportMsgType();
  MessageProtobuf.Msg handshakeMsg=imsClient.getHandshakeMsg();
  if (handshakeMsg != null && handshakeMsg.getHead() != null) {
    handshakeMsgType=handshakeMsg.getHead().getMsgType();
  }
  MessageProtobuf.Msg heartbeatMsg=imsClient.getHeartbeatMsg();
  if (heartbeatMsg != null && heartbeatMsg.getHead() != null) {
    heartbeatMsgType=heartbeatMsg.getHead().getMsgType();
  }
  int msgType=msg.getHead().getMsgType();
  if (msgType == handshakeMsgType || msgType == heartbeatMsgType || msgType == clientReceivedReportMsgType) {
    return;
  }
  String msgId=msg.getHead().getMsgId();
  if (!mMsgTimeoutMap.containsKey(msgId)) {
    MsgTimeoutTimer timer=new MsgTimeoutTimer(imsClient,msg);
    mMsgTimeoutMap.put(msgId,timer);
  }
  System.out.println("?????????????message=" + msg + "\t?????????" + mMsgTimeoutMap.size());
}
