public static boolean canAddMessageToMedia(TLRPC.Message message){
  if (message instanceof TLRPC.TL_message_secret && (message.media instanceof TLRPC.TL_messageMediaPhoto || MessageObject.isVideoMessage(message) || MessageObject.isGifMessage(message)) && message.media.ttl_seconds != 0 && message.media.ttl_seconds <= 60) {
    return false;
  }
 else   if (!(message instanceof TLRPC.TL_message_secret) && message instanceof TLRPC.TL_message && (message.media instanceof TLRPC.TL_messageMediaPhoto || message.media instanceof TLRPC.TL_messageMediaDocument) && message.media.ttl_seconds != 0) {
    return false;
  }
 else   if (message.media instanceof TLRPC.TL_messageMediaPhoto || message.media instanceof TLRPC.TL_messageMediaDocument && !MessageObject.isGifDocument(message.media.document)) {
    return true;
  }
 else   if (!message.entities.isEmpty()) {
    for (int a=0; a < message.entities.size(); a++) {
      TLRPC.MessageEntity entity=message.entities.get(a);
      if (entity instanceof TLRPC.TL_messageEntityUrl || entity instanceof TLRPC.TL_messageEntityTextUrl || entity instanceof TLRPC.TL_messageEntityEmail) {
        return true;
      }
    }
  }
  return false;
}
