public void processChatInfo(int chat_id,final TLRPC.ChatFull info,final ArrayList<TLRPC.User> usersArr,final boolean fromCache,boolean force,final boolean byChannelUsers,final MessageObject pinnedMessageObject){
  if (fromCache && chat_id > 0 && !byChannelUsers) {
    loadFullChat(chat_id,0,force);
  }
  if (info != null) {
    AndroidUtilities.runOnUIThread(() -> {
      putUsers(usersArr,fromCache);
      if (info.stickerset != null) {
        DataQuery.getInstance(currentAccount).getGroupStickerSetById(info.stickerset);
      }
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,info,0,byChannelUsers,pinnedMessageObject);
    }
);
  }
}
