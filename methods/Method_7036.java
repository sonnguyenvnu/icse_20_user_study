private void fixUnsupportedMedia(TLRPC.Message message){
  if (message == null) {
    return;
  }
  boolean ok=false;
  if (message.media instanceof TLRPC.TL_messageMediaUnsupported_old) {
    if (message.media.bytes.length == 0) {
      message.media.bytes=new byte[1];
      message.media.bytes[0]=TLRPC.LAYER;
    }
  }
 else   if (message.media instanceof TLRPC.TL_messageMediaUnsupported) {
    message.media=new TLRPC.TL_messageMediaUnsupported_old();
    message.media.bytes=new byte[1];
    message.media.bytes[0]=TLRPC.LAYER;
    message.flags|=TLRPC.MESSAGE_FLAG_HAS_MEDIA;
  }
}
