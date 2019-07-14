public static boolean canEditMessageAnytime(int currentAccount,TLRPC.Message message,TLRPC.Chat chat){
  if (message == null || message.to_id == null || message.media != null && (isRoundVideoDocument(message.media.document) || isStickerDocument(message.media.document) || isAnimatedStickerDocument(message.media.document)) || message.action != null && !(message.action instanceof TLRPC.TL_messageActionEmpty) || isForwardedMessage(message) || message.via_bot_id != 0 || message.id < 0) {
    return false;
  }
  if (message.from_id == message.to_id.user_id && message.from_id == UserConfig.getInstance(currentAccount).getClientUserId() && !isLiveLocationMessage(message)) {
    return true;
  }
  if (chat == null && message.to_id.channel_id != 0) {
    chat=MessagesController.getInstance(UserConfig.selectedAccount).getChat(message.to_id.channel_id);
    if (chat == null) {
      return false;
    }
  }
  if (message.out && chat != null && chat.megagroup && (chat.creator || chat.admin_rights != null && chat.admin_rights.pin_messages)) {
    return true;
  }
  return false;
}
