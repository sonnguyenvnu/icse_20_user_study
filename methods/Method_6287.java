public static boolean isChannel(int chatId,int currentAccount){
  TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(chatId);
  return chat instanceof TLRPC.TL_channel || chat instanceof TLRPC.TL_channelForbidden;
}
