public static boolean canEditMessage(int currentAccount,TLRPC.Message message,TLRPC.Chat chat){
  if (chat != null && (chat.left || chat.kicked)) {
    return false;
  }
  if (message == null || message.to_id == null || message.media != null && (isRoundVideoDocument(message.media.document) || isStickerDocument(message.media.document) || isAnimatedStickerDocument(message.media.document) || isLocationMessage(message)) || message.action != null && !(message.action instanceof TLRPC.TL_messageActionEmpty) || isForwardedMessage(message) || message.via_bot_id != 0 || message.id < 0) {
    return false;
  }
  if (message.from_id == message.to_id.user_id && message.from_id == UserConfig.getInstance(currentAccount).getClientUserId() && !isLiveLocationMessage(message) && !(message.media instanceof TLRPC.TL_messageMediaContact)) {
    return true;
  }
  if (chat == null && message.to_id.channel_id != 0) {
    chat=MessagesController.getInstance(currentAccount).getChat(message.to_id.channel_id);
    if (chat == null) {
      return false;
    }
  }
  if (message.media != null && !(message.media instanceof TLRPC.TL_messageMediaEmpty) && !(message.media instanceof TLRPC.TL_messageMediaPhoto) && !(message.media instanceof TLRPC.TL_messageMediaDocument) && !(message.media instanceof TLRPC.TL_messageMediaWebPage)) {
    return false;
  }
  if (message.out && chat != null && chat.megagroup && (chat.creator || chat.admin_rights != null && chat.admin_rights.pin_messages)) {
    return true;
  }
  if (Math.abs(message.date - ConnectionsManager.getInstance(currentAccount).getCurrentTime()) > MessagesController.getInstance(currentAccount).maxEditTime) {
    return false;
  }
  if (message.to_id.channel_id == 0) {
    return (message.out || message.from_id == UserConfig.getInstance(currentAccount).getClientUserId()) && (message.media instanceof TLRPC.TL_messageMediaPhoto || message.media instanceof TLRPC.TL_messageMediaDocument && !isStickerMessage(message) && !isAnimatedStickerMessage(message) || message.media instanceof TLRPC.TL_messageMediaEmpty || message.media instanceof TLRPC.TL_messageMediaWebPage || message.media == null);
  }
  if (chat.megagroup && message.out || !chat.megagroup && (chat.creator || chat.admin_rights != null && (chat.admin_rights.edit_messages || message.out)) && message.post) {
    if (message.media instanceof TLRPC.TL_messageMediaPhoto || message.media instanceof TLRPC.TL_messageMediaDocument && !isStickerMessage(message) && !isAnimatedStickerMessage(message) || message.media instanceof TLRPC.TL_messageMediaEmpty || message.media instanceof TLRPC.TL_messageMediaWebPage || message.media == null) {
      return true;
    }
  }
  return false;
}
