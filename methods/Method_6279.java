public static boolean canChangeChatInfo(TLRPC.Chat chat){
  return canUserDoAction(chat,ACTION_CHANGE_INFO);
}
