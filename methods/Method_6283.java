public static boolean canSendMessages(TLRPC.Chat chat){
  return canUserDoAction(chat,ACTION_SEND);
}
