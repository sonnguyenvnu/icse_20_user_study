public static boolean isSecretMedia(TLRPC.Message message){
  if (message instanceof TLRPC.TL_message_secret) {
    return (message.media instanceof TLRPC.TL_messageMediaPhoto || isRoundVideoMessage(message) || isVideoMessage(message)) && message.media.ttl_seconds != 0;
  }
 else   if (message instanceof TLRPC.TL_message) {
    return (message.media instanceof TLRPC.TL_messageMediaPhoto || message.media instanceof TLRPC.TL_messageMediaDocument) && message.media.ttl_seconds != 0;
  }
  return false;
}
