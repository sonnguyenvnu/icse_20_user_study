private static Message read(int mcsTag,byte[] bytes,int len) throws IOException {
  Wire wire=new Wire();
  try {
switch (mcsTag) {
case MCS_HEARTBEAT_PING_TAG:
      return wire.parseFrom(bytes,0,len,HeartbeatPing.class);
case MCS_HEARTBEAT_ACK_TAG:
    return wire.parseFrom(bytes,0,len,HeartbeatAck.class);
case MCS_LOGIN_REQUEST_TAG:
  return wire.parseFrom(bytes,0,len,LoginRequest.class);
case MCS_LOGIN_RESPONSE_TAG:
return wire.parseFrom(bytes,0,len,LoginResponse.class);
case MCS_CLOSE_TAG:
return wire.parseFrom(bytes,0,len,Close.class);
case MCS_IQ_STANZA_TAG:
return wire.parseFrom(bytes,0,len,IqStanza.class);
case MCS_DATA_MESSAGE_STANZA_TAG:
return wire.parseFrom(bytes,0,len,DataMessageStanza.class);
default :
Log.w(TAG,"Unknown tag: " + mcsTag);
return null;
}
}
 catch (IllegalStateException e) {
Log.w(TAG,"Error parsing tag: " + mcsTag,e);
return null;
}
}
