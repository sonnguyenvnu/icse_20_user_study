public static boolean canWriteToChat(TLRPC.Chat chat){
  return !isChannel(chat) || chat.creator || chat.admin_rights != null && chat.admin_rights.post_messages || !chat.broadcast;
}
