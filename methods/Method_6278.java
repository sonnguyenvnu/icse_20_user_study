public static boolean hasAdminRights(TLRPC.Chat chat){
  return chat != null && (chat.creator || chat.admin_rights != null && chat.admin_rights.flags != 0);
}
