public void sortOnlineContacts(){
  if (onlineContacts == null) {
    return;
  }
  try {
    int currentTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    MessagesController messagesController=MessagesController.getInstance(currentAccount);
    Collections.sort(onlineContacts,(o1,o2) -> {
      TLRPC.User user1=messagesController.getUser(o2.user_id);
      TLRPC.User user2=messagesController.getUser(o1.user_id);
      int status1=0;
      int status2=0;
      if (user1 != null) {
        if (user1.self) {
          status1=currentTime + 50000;
        }
 else         if (user1.status != null) {
          status1=user1.status.expires;
        }
      }
      if (user2 != null) {
        if (user2.self) {
          status2=currentTime + 50000;
        }
 else         if (user2.status != null) {
          status2=user2.status.expires;
        }
      }
      if (status1 > 0 && status2 > 0) {
        if (status1 > status2) {
          return 1;
        }
 else         if (status1 < status2) {
          return -1;
        }
        return 0;
      }
 else       if (status1 < 0 && status2 < 0) {
        if (status1 > status2) {
          return 1;
        }
 else         if (status1 < status2) {
          return -1;
        }
        return 0;
      }
 else       if (status1 < 0 && status2 > 0 || status1 == 0 && status2 != 0) {
        return -1;
      }
 else       if (status2 < 0 && status1 > 0 || status2 == 0 && status1 != 0) {
        return 1;
      }
      return 0;
    }
);
    notifyDataSetChanged();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
