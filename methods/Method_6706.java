public void generateGameMessageText(TLRPC.User fromUser){
  if (fromUser == null) {
    if (messageOwner.from_id > 0) {
      fromUser=MessagesController.getInstance(currentAccount).getUser(messageOwner.from_id);
    }
  }
  TLRPC.TL_game game=null;
  if (replyMessageObject != null && replyMessageObject.messageOwner.media != null && replyMessageObject.messageOwner.media.game != null) {
    game=replyMessageObject.messageOwner.media.game;
  }
  if (game == null) {
    if (fromUser != null && fromUser.id == UserConfig.getInstance(currentAccount).getClientUserId()) {
      messageText=LocaleController.formatString("ActionYouScored",R.string.ActionYouScored,LocaleController.formatPluralString("Points",messageOwner.action.score));
    }
 else {
      messageText=replaceWithLink(LocaleController.formatString("ActionUserScored",R.string.ActionUserScored,LocaleController.formatPluralString("Points",messageOwner.action.score)),"un1",fromUser);
    }
  }
 else {
    if (fromUser != null && fromUser.id == UserConfig.getInstance(currentAccount).getClientUserId()) {
      messageText=LocaleController.formatString("ActionYouScoredInGame",R.string.ActionYouScoredInGame,LocaleController.formatPluralString("Points",messageOwner.action.score));
    }
 else {
      messageText=replaceWithLink(LocaleController.formatString("ActionUserScoredInGame",R.string.ActionUserScoredInGame,LocaleController.formatPluralString("Points",messageOwner.action.score)),"un1",fromUser);
    }
    messageText=replaceWithLink(messageText,"un2",game);
  }
}
