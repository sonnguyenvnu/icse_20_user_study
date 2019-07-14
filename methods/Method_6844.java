public void getBlockedUsers(boolean cache){
  if (!UserConfig.getInstance(currentAccount).isClientActivated() || loadingBlockedUsers) {
    return;
  }
  loadingBlockedUsers=true;
  if (cache) {
    MessagesStorage.getInstance(currentAccount).getBlockedUsers();
  }
 else {
    TLRPC.TL_contacts_getBlocked req=new TLRPC.TL_contacts_getBlocked();
    req.offset=0;
    req.limit=200;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      SparseIntArray blocked=new SparseIntArray();
      ArrayList<TLRPC.User> users=null;
      if (error == null) {
        final TLRPC.contacts_Blocked res=(TLRPC.contacts_Blocked)response;
        for (        TLRPC.TL_contactBlocked contactBlocked : res.blocked) {
          blocked.put(contactBlocked.user_id,1);
        }
        users=res.users;
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,null,true,true);
        MessagesStorage.getInstance(currentAccount).putBlockedUsers(blocked,true);
      }
      processLoadedBlockedUsers(blocked,users,false);
    }
);
  }
}
