public static boolean isGameMessage(TLRPC.Message message){
  return message.media instanceof TLRPC.TL_messageMediaGame;
}
