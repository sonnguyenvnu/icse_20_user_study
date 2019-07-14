protected void processUpdateEncryption(TLRPC.TL_updateEncryption update,ConcurrentHashMap<Integer,TLRPC.User> usersDict){
  final TLRPC.EncryptedChat newChat=update.chat;
  long dialog_id=((long)newChat.id) << 32;
  TLRPC.EncryptedChat existingChat=MessagesController.getInstance(currentAccount).getEncryptedChatDB(newChat.id,false);
  if (newChat instanceof TLRPC.TL_encryptedChatRequested && existingChat == null) {
    int user_id=newChat.participant_id;
    if (user_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
      user_id=newChat.admin_id;
    }
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
    if (user == null) {
      user=usersDict.get(user_id);
    }
    newChat.user_id=user_id;
    final TLRPC.Dialog dialog=new TLRPC.TL_dialog();
    dialog.id=dialog_id;
    dialog.unread_count=0;
    dialog.top_message=0;
    dialog.last_message_date=update.date;
    MessagesController.getInstance(currentAccount).putEncryptedChat(newChat,false);
    AndroidUtilities.runOnUIThread(() -> {
      MessagesController.getInstance(currentAccount).dialogs_dict.put(dialog.id,dialog);
      MessagesController.getInstance(currentAccount).allDialogs.add(dialog);
      MessagesController.getInstance(currentAccount).sortDialogs(null);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
    }
);
    MessagesStorage.getInstance(currentAccount).putEncryptedChat(newChat,user,dialog);
    acceptSecretChat(newChat);
  }
 else   if (newChat instanceof TLRPC.TL_encryptedChat) {
    if (existingChat instanceof TLRPC.TL_encryptedChatWaiting && (existingChat.auth_key == null || existingChat.auth_key.length == 1)) {
      newChat.a_or_b=existingChat.a_or_b;
      newChat.user_id=existingChat.user_id;
      processAcceptedSecretChat(newChat);
    }
 else     if (existingChat == null && startingSecretChat) {
      delayedEncryptedChatUpdates.add(update);
    }
  }
 else {
    final TLRPC.EncryptedChat exist=existingChat;
    if (exist != null) {
      newChat.user_id=exist.user_id;
      newChat.auth_key=exist.auth_key;
      newChat.key_create_date=exist.key_create_date;
      newChat.key_use_count_in=exist.key_use_count_in;
      newChat.key_use_count_out=exist.key_use_count_out;
      newChat.ttl=exist.ttl;
      newChat.seq_in=exist.seq_in;
      newChat.seq_out=exist.seq_out;
      newChat.admin_id=exist.admin_id;
      newChat.mtproto_seq=exist.mtproto_seq;
    }
    AndroidUtilities.runOnUIThread(() -> {
      if (exist != null) {
        MessagesController.getInstance(currentAccount).putEncryptedChat(newChat,false);
      }
      MessagesStorage.getInstance(currentAccount).updateEncryptedChat(newChat);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated,newChat);
    }
);
  }
}
