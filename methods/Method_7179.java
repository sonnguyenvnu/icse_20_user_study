protected void processUnsentMessages(final ArrayList<TLRPC.Message> messages,final ArrayList<TLRPC.User> users,final ArrayList<TLRPC.Chat> chats,final ArrayList<TLRPC.EncryptedChat> encryptedChats){
  AndroidUtilities.runOnUIThread(() -> {
    MessagesController.getInstance(currentAccount).putUsers(users,true);
    MessagesController.getInstance(currentAccount).putChats(chats,true);
    MessagesController.getInstance(currentAccount).putEncryptedChats(encryptedChats,true);
    for (int a=0; a < messages.size(); a++) {
      TLRPC.Message message=messages.get(a);
      MessageObject messageObject=new MessageObject(currentAccount,message,false);
      retrySendMessage(messageObject,true);
    }
  }
);
}
