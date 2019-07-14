public static boolean isMediaEmpty(TLRPC.Message message){
  return message == null || message.media == null || message.media instanceof TLRPC.TL_messageMediaEmpty || message.media instanceof TLRPC.TL_messageMediaWebPage;
}
