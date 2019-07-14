public static boolean shouldEncryptPhotoOrVideo(TLRPC.Message message){
  if (message instanceof TLRPC.TL_message_secret) {
    return (message.media instanceof TLRPC.TL_messageMediaPhoto || isVideoMessage(message)) && message.ttl > 0 && message.ttl <= 60;
  }
 else {
    return (message.media instanceof TLRPC.TL_messageMediaPhoto || message.media instanceof TLRPC.TL_messageMediaDocument) && message.media.ttl_seconds != 0;
  }
}
