public static boolean canDeleteMessage(int currentAccount,TLRPC.Message message,TLRPC.Chat chat){
  if (message.id < 0) {
    return true;
  }
  if (chat == null && message.to_id.channel_id != 0) {
    chat=MessagesController.getInstance(currentAccount).getChat(message.to_id.channel_id);
  }
  if (ChatObject.isChannel(chat)) {
    return message.id != 1 && (chat.creator || chat.admin_rights != null && (chat.admin_rights.delete_messages || message.out) || chat.megagroup && message.out && message.from_id > 0);
  }
  return isOut(message) || !ChatObject.isChannel(chat);
}
