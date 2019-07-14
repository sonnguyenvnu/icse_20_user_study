public static boolean isLiveLocationMessage(TLRPC.Message message){
  return message.media instanceof TLRPC.TL_messageMediaGeoLive;
}
