public static boolean canPost(TLRPC.Chat chat){
  return canUserDoAction(chat,ACTION_POST);
}
