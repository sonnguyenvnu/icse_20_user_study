private void broadcastReplyMessages(final ArrayList<TLRPC.Message> result,final SparseArray<ArrayList<MessageObject>> replyMessageOwners,final ArrayList<TLRPC.User> users,final ArrayList<TLRPC.Chat> chats,final long dialog_id,final boolean isCache){
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
  AndroidUtilities.runOnUIThread(() -> {
    MessagesController.getInstance(currentAccount).putUsers(users,isCache);
    MessagesController.getInstance(currentAccount).putChats(chats,isCache);
    boolean changed=false;
    for (int a=0; a < result.size(); a++) {
      TLRPC.Message message=result.get(a);
      ArrayList<MessageObject> arrayList=replyMessageOwners.get(message.id);
      if (arrayList != null) {
        MessageObject messageObject=new MessageObject(currentAccount,message,usersDict,chatsDict,false);
        for (int b=0; b < arrayList.size(); b++) {
          MessageObject m=arrayList.get(b);
          m.replyMessageObject=messageObject;
          if (m.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage) {
            m.generatePinMessageText(null,null);
          }
 else           if (m.messageOwner.action instanceof TLRPC.TL_messageActionGameScore) {
            m.generateGameMessageText(null);
          }
 else           if (m.messageOwner.action instanceof TLRPC.TL_messageActionPaymentSent) {
            m.generatePaymentSentMessageText(null);
          }
          if (m.isMegagroup()) {
            m.replyMessageObject.messageOwner.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
          }
        }
        changed=true;
      }
    }
    if (changed) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replyMessagesDidLoad,dialog_id);
    }
  }
);
}
