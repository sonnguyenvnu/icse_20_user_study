public void processUserInfo(TLRPC.User user,final TLRPC.UserFull info,final boolean fromCache,boolean force,final MessageObject pinnedMessageObject,int classGuid){
  if (fromCache) {
    loadFullUser(user,classGuid,force);
  }
  if (info != null) {
    if (fullUsers.get(user.id) == null) {
      fullUsers.put(user.id,info);
    }
    AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad,user.id,info,pinnedMessageObject));
  }
}
