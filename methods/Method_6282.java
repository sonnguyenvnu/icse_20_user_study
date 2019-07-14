public static boolean canSendPolls(TLRPC.Chat chat){
  return canUserDoAction(chat,ACTION_SEND_POLLS);
}
