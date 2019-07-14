private void processLoadedMedia(final TLRPC.messages_Messages res,final long uid,int count,int max_id,final int type,final int fromCache,final int classGuid,final boolean isChannel,final boolean topReached){
  int lower_part=(int)uid;
  if (fromCache != 0 && res.messages.isEmpty() && lower_part != 0) {
    if (fromCache == 2) {
      return;
    }
    loadMedia(uid,count,max_id,type,0,classGuid);
  }
 else {
    if (fromCache == 0) {
      ImageLoader.saveMessagesThumbs(res.messages);
      MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
      putMediaDatabase(uid,type,res.messages,max_id,topReached);
    }
    final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
    for (int a=0; a < res.users.size(); a++) {
      TLRPC.User u=res.users.get(a);
      usersDict.put(u.id,u);
    }
    final ArrayList<MessageObject> objects=new ArrayList<>();
    for (int a=0; a < res.messages.size(); a++) {
      TLRPC.Message message=res.messages.get(a);
      objects.add(new MessageObject(currentAccount,message,usersDict,true));
    }
    AndroidUtilities.runOnUIThread(() -> {
      int totalCount=res.count;
      MessagesController.getInstance(currentAccount).putUsers(res.users,fromCache != 0);
      MessagesController.getInstance(currentAccount).putChats(res.chats,fromCache != 0);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.mediaDidLoad,uid,totalCount,objects,classGuid,type,topReached);
    }
);
  }
}
