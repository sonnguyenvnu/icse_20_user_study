public void generatePinMessageText(TLRPC.User fromUser,TLRPC.Chat chat){
  if (fromUser == null && chat == null) {
    if (messageOwner.from_id > 0) {
      fromUser=MessagesController.getInstance(currentAccount).getUser(messageOwner.from_id);
    }
    if (fromUser == null) {
      chat=MessagesController.getInstance(currentAccount).getChat(messageOwner.to_id.channel_id);
    }
  }
  if (replyMessageObject == null || replyMessageObject.messageOwner instanceof TLRPC.TL_messageEmpty || replyMessageObject.messageOwner.action instanceof TLRPC.TL_messageActionHistoryClear) {
    messageText=replaceWithLink(LocaleController.getString("ActionPinnedNoText",R.string.ActionPinnedNoText),"un1",fromUser != null ? fromUser : chat);
  }
 else {
    if (replyMessageObject.isMusic()) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedMusic",R.string.ActionPinnedMusic),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.isVideo()) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedVideo",R.string.ActionPinnedVideo),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.isGif()) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedGif",R.string.ActionPinnedGif),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.isVoice()) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedVoice",R.string.ActionPinnedVoice),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.isRoundVideo()) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedRound",R.string.ActionPinnedRound),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.isSticker() || replyMessageObject.isAnimatedSticker()) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedSticker",R.string.ActionPinnedSticker),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedFile",R.string.ActionPinnedFile),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeo) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedGeo",R.string.ActionPinnedGeo),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedGeoLive",R.string.ActionPinnedGeoLive),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedContact",R.string.ActionPinnedContact),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedPoll",R.string.ActionPinnedPoll),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedPhoto",R.string.ActionPinnedPhoto),"un1",fromUser != null ? fromUser : chat);
    }
 else     if (replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
      messageText=replaceWithLink(LocaleController.formatString("ActionPinnedGame",R.string.ActionPinnedGame,"\uD83C\uDFAE " + replyMessageObject.messageOwner.media.game.title),"un1",fromUser != null ? fromUser : chat);
      messageText=Emoji.replaceEmoji(messageText,Theme.chat_msgTextPaint.getFontMetricsInt(),AndroidUtilities.dp(20),false);
    }
 else     if (replyMessageObject.messageText != null && replyMessageObject.messageText.length() > 0) {
      CharSequence mess=replyMessageObject.messageText;
      if (mess.length() > 20) {
        mess=mess.subSequence(0,20) + "...";
      }
      mess=Emoji.replaceEmoji(mess,Theme.chat_msgTextPaint.getFontMetricsInt(),AndroidUtilities.dp(20),false);
      messageText=replaceWithLink(LocaleController.formatString("ActionPinnedText",R.string.ActionPinnedText,mess),"un1",fromUser != null ? fromUser : chat);
    }
 else {
      messageText=replaceWithLink(LocaleController.getString("ActionPinnedNoText",R.string.ActionPinnedNoText),"un1",fromUser != null ? fromUser : chat);
    }
  }
}
