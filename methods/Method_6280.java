public static boolean canAddAdmins(TLRPC.Chat chat){
  return canUserDoAction(chat,ACTION_ADD_ADMINS);
}
