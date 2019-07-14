public static boolean canUserDoAction(TLRPC.Chat chat,int action){
  if (chat == null) {
    return true;
  }
  if (canUserDoAdminAction(chat,action)) {
    return true;
  }
  if (getBannedRight(chat.banned_rights,action)) {
    return false;
  }
  if (isBannableAction(action)) {
    if (chat.admin_rights != null && !isAdminAction(action)) {
      return true;
    }
    if (chat.default_banned_rights == null && (chat instanceof TLRPC.TL_chat_layer92 || chat instanceof TLRPC.TL_chat_old || chat instanceof TLRPC.TL_chat_old2 || chat instanceof TLRPC.TL_channel_layer92 || chat instanceof TLRPC.TL_channel_layer77 || chat instanceof TLRPC.TL_channel_layer72 || chat instanceof TLRPC.TL_channel_layer67 || chat instanceof TLRPC.TL_channel_layer48 || chat instanceof TLRPC.TL_channel_old)) {
      return true;
    }
    if (chat.default_banned_rights == null || getBannedRight(chat.default_banned_rights,action)) {
      return false;
    }
    return true;
  }
  return false;
}
