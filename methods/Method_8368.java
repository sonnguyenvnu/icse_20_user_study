private void processDone(){
  if (type != TYPE_KICKED) {
    return;
  }
  String newBannedRights=ChatObject.getBannedRightsString(defaultBannedRights);
  if (!newBannedRights.equals(initialBannedRights)) {
    MessagesController.getInstance(currentAccount).setDefaultBannedRole(chatId,defaultBannedRights,ChatObject.isChannel(currentChat),this);
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(chatId);
    if (chat != null) {
      chat.default_banned_rights=defaultBannedRights;
    }
  }
  finishFragment();
}
