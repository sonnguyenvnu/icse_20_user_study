public static boolean canPinMessages(TLRPC.Chat chat){
  return canUserDoAction(chat,ACTION_PIN) || ChatObject.isChannel(chat) && !chat.megagroup && chat.admin_rights != null && chat.admin_rights.edit_messages;
}
