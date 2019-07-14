public static boolean isCanWriteToChannel(int chatId,int currentAccount){
  TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(chatId);
  return ChatObject.canSendMessages(chat) || chat != null && chat.megagroup;
}
