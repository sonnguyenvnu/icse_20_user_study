public void blockUser(int user_id){
  final TLRPC.User user=getUser(user_id);
  if (user == null || blockedUsers.indexOfKey(user_id) >= 0) {
    return;
  }
  blockedUsers.put(user_id,1);
  if (user.bot) {
    DataQuery.getInstance(currentAccount).removeInline(user_id);
  }
 else {
    DataQuery.getInstance(currentAccount).removePeer(user_id);
  }
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoad);
  TLRPC.TL_contacts_block req=new TLRPC.TL_contacts_block();
  req.id=getInputUser(user);
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      SparseIntArray ids=new SparseIntArray();
      ids.put(user.id,1);
      MessagesStorage.getInstance(currentAccount).putBlockedUsers(ids,false);
    }
  }
);
}
