private MessageObject broadcastPinnedMessage(final TLRPC.Message result,final ArrayList<TLRPC.User> users,final ArrayList<TLRPC.Chat> chats,final boolean isCache,boolean returnValue){
  final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
  for (int a=0; a < users.size(); a++) {
    TLRPC.User user=users.get(a);
    usersDict.put(user.id,user);
  }
  final SparseArray<TLRPC.Chat> chatsDict=new SparseArray<>();
  for (int a=0; a < chats.size(); a++) {
    TLRPC.Chat chat=chats.get(a);
    chatsDict.put(chat.id,chat);
  }
  if (returnValue) {
    return new MessageObject(currentAccount,result,usersDict,chatsDict,false);
  }
 else {
    AndroidUtilities.runOnUIThread(() -> {
      MessagesController.getInstance(currentAccount).putUsers(users,isCache);
      MessagesController.getInstance(currentAccount).putChats(chats,isCache);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.pinnedMessageDidLoad,new MessageObject(currentAccount,result,usersDict,chatsDict,false));
    }
);
  }
  return null;
}
