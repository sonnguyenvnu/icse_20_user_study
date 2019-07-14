public void loadFullUser(final TLRPC.User user,final int classGuid,boolean force){
  if (user == null || loadingFullUsers.contains(user.id) || !force && loadedFullUsers.contains(user.id)) {
    return;
  }
  loadingFullUsers.add(user.id);
  TLRPC.TL_users_getFullUser req=new TLRPC.TL_users_getFullUser();
  req.id=getInputUser(user);
  long dialog_id=user.id;
  if (dialogs_read_inbox_max.get(dialog_id) == null || dialogs_read_outbox_max.get(dialog_id) == null) {
    reloadDialogsReadValue(null,dialog_id);
  }
  int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      TLRPC.UserFull userFull=(TLRPC.UserFull)response;
      MessagesStorage.getInstance(currentAccount).updateUserInfo(userFull,false);
      AndroidUtilities.runOnUIThread(() -> {
        applyDialogNotificationsSettings(user.id,userFull.notify_settings);
        if (userFull.bot_info instanceof TLRPC.TL_botInfo) {
          DataQuery.getInstance(currentAccount).putBotInfo(userFull.bot_info);
        }
        int index=blockedUsers.indexOfKey(user.id);
        if (userFull.blocked) {
          if (index < 0) {
            SparseIntArray ids=new SparseIntArray();
            ids.put(user.id,1);
            MessagesStorage.getInstance(currentAccount).putBlockedUsers(ids,false);
            blockedUsers.put(user.id,1);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoad);
          }
        }
 else {
          if (index >= 0) {
            MessagesStorage.getInstance(currentAccount).deleteBlockedUser(user.id);
            blockedUsers.removeAt(index);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoad);
          }
        }
        fullUsers.put(user.id,userFull);
        loadingFullUsers.remove((Integer)user.id);
        loadedFullUsers.add(user.id);
        String names=user.first_name + user.last_name + user.username;
        ArrayList<TLRPC.User> users=new ArrayList<>();
        users.add(userFull.user);
        putUsers(users,false);
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(users,null,false,true);
        if (names != null && !names.equals(userFull.user.first_name + userFull.user.last_name + userFull.user.username)) {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_NAME);
        }
        if (userFull.bot_info instanceof TLRPC.TL_botInfo) {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.botInfoDidLoad,userFull.bot_info,classGuid);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad,user.id,userFull,null);
      }
);
    }
 else {
      AndroidUtilities.runOnUIThread(() -> loadingFullUsers.remove((Integer)user.id));
    }
  }
);
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
}
