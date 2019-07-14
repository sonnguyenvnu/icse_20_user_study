private void kickUser(int uid){
  if (uid != 0) {
    MessagesController.getInstance(currentAccount).deleteUserFromChat(chat_id,MessagesController.getInstance(currentAccount).getUser(uid),chatInfo);
  }
 else {
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.closeChats);
    if (AndroidUtilities.isTablet()) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats,-(long)chat_id);
    }
 else {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
    }
    MessagesController.getInstance(currentAccount).deleteUserFromChat(chat_id,MessagesController.getInstance(currentAccount).getUser(UserConfig.getInstance(currentAccount).getClientUserId()),chatInfo);
    playProfileAnimation=false;
    finishFragment();
  }
}
