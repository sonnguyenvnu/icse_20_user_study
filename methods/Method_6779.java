public static boolean isLocationMessage(TLRPC.Message message){
  return message.media instanceof TLRPC.TL_messageMediaGeo || message.media instanceof TLRPC.TL_messageMediaGeoLive || message.media instanceof TLRPC.TL_messageMediaVenue;
}
