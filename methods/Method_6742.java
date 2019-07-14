public boolean needDrawShareButton(){
  if (eventId != 0) {
    return false;
  }
 else   if (messageOwner.fwd_from != null && !isOutOwner() && messageOwner.fwd_from.saved_from_peer != null && getDialogId() == UserConfig.getInstance(currentAccount).getClientUserId()) {
    return true;
  }
 else   if (type == TYPE_STICKER || type == TYPE_ANIMATED_STICKER) {
    return false;
  }
 else   if (messageOwner.fwd_from != null && messageOwner.fwd_from.channel_id != 0 && !isOutOwner()) {
    return true;
  }
 else   if (isFromUser()) {
    if (messageOwner.media instanceof TLRPC.TL_messageMediaEmpty || messageOwner.media == null || messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && !(messageOwner.media.webpage instanceof TLRPC.TL_webPage)) {
      return false;
    }
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(messageOwner.from_id);
    if (user != null && user.bot) {
      return true;
    }
    if (!isOut()) {
      if (messageOwner.media instanceof TLRPC.TL_messageMediaGame || messageOwner.media instanceof TLRPC.TL_messageMediaInvoice) {
        return true;
      }
      if (isMegagroup()) {
        TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(messageOwner.to_id.channel_id);
        return chat != null && chat.username != null && chat.username.length() > 0 && !(messageOwner.media instanceof TLRPC.TL_messageMediaContact) && !(messageOwner.media instanceof TLRPC.TL_messageMediaGeo);
      }
    }
  }
 else   if (messageOwner.from_id < 0 || messageOwner.post) {
    if (messageOwner.to_id.channel_id != 0 && (messageOwner.via_bot_id == 0 && messageOwner.reply_to_msg_id == 0 || type != TYPE_STICKER && type != TYPE_ANIMATED_STICKER)) {
      return true;
    }
  }
  return false;
}
