public boolean processUpdateArray(ArrayList<TLRPC.Update> updates,final ArrayList<TLRPC.User> usersArr,final ArrayList<TLRPC.Chat> chatsArr,boolean fromGetDifference){
  if (updates.isEmpty()) {
    if (usersArr != null || chatsArr != null) {
      AndroidUtilities.runOnUIThread(() -> {
        putUsers(usersArr,false);
        putChats(chatsArr,false);
      }
);
    }
    return true;
  }
  long currentTime=System.currentTimeMillis();
  boolean printChanged=false;
  LongSparseArray<ArrayList<MessageObject>> messages=null;
  LongSparseArray<TLRPC.WebPage> webPages=null;
  ArrayList<MessageObject> pushMessages=null;
  ArrayList<TLRPC.Message> messagesArr=null;
  LongSparseArray<ArrayList<MessageObject>> editingMessages=null;
  SparseArray<SparseIntArray> channelViews=null;
  SparseLongArray markAsReadMessagesInbox=null;
  SparseLongArray markAsReadMessagesOutbox=null;
  ArrayList<Long> markAsReadMessages=null;
  SparseIntArray markAsReadEncrypted=null;
  SparseArray<ArrayList<Integer>> deletedMessages=null;
  SparseIntArray clearHistoryMessages=null;
  ArrayList<TLRPC.ChatParticipants> chatInfoToUpdate=null;
  ArrayList<TLRPC.Update> updatesOnMainThread=null;
  ArrayList<TLRPC.TL_updateEncryptedMessagesRead> tasks=null;
  ArrayList<Integer> contactsIds=null;
  boolean checkForUsers=true;
  ConcurrentHashMap<Integer,TLRPC.User> usersDict;
  ConcurrentHashMap<Integer,TLRPC.Chat> chatsDict;
  if (usersArr != null) {
    usersDict=new ConcurrentHashMap<>();
    for (int a=0, size=usersArr.size(); a < size; a++) {
      TLRPC.User user=usersArr.get(a);
      usersDict.put(user.id,user);
    }
  }
 else {
    checkForUsers=false;
    usersDict=users;
  }
  if (chatsArr != null) {
    chatsDict=new ConcurrentHashMap<>();
    for (int a=0, size=chatsArr.size(); a < size; a++) {
      TLRPC.Chat chat=chatsArr.get(a);
      chatsDict.put(chat.id,chat);
    }
  }
 else {
    checkForUsers=false;
    chatsDict=chats;
  }
  if (fromGetDifference) {
    checkForUsers=false;
  }
  if (usersArr != null || chatsArr != null) {
    AndroidUtilities.runOnUIThread(() -> {
      putUsers(usersArr,false);
      putChats(chatsArr,false);
    }
);
  }
  int interfaceUpdateMask=0;
  for (int c=0, size3=updates.size(); c < size3; c++) {
    TLRPC.Update baseUpdate=updates.get(c);
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("process update " + baseUpdate);
    }
    if (baseUpdate instanceof TLRPC.TL_updateNewMessage || baseUpdate instanceof TLRPC.TL_updateNewChannelMessage) {
      TLRPC.Message message;
      if (baseUpdate instanceof TLRPC.TL_updateNewMessage) {
        message=((TLRPC.TL_updateNewMessage)baseUpdate).message;
      }
 else {
        message=((TLRPC.TL_updateNewChannelMessage)baseUpdate).message;
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d(baseUpdate + " channelId = " + message.to_id.channel_id);
        }
        if (!message.out && message.from_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
          message.out=true;
        }
      }
      TLRPC.Chat chat=null;
      int chat_id=0;
      int user_id=0;
      if (message.to_id.channel_id != 0) {
        chat_id=message.to_id.channel_id;
      }
 else       if (message.to_id.chat_id != 0) {
        chat_id=message.to_id.chat_id;
      }
 else       if (message.to_id.user_id != 0) {
        user_id=message.to_id.user_id;
      }
      if (chat_id != 0) {
        chat=chatsDict.get(chat_id);
        if (chat == null) {
          chat=getChat(chat_id);
        }
        if (chat == null) {
          chat=MessagesStorage.getInstance(currentAccount).getChatSync(chat_id);
          putChat(chat,true);
        }
      }
      if (checkForUsers) {
        if (chat_id != 0) {
          if (chat == null) {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("not found chat " + chat_id);
            }
            return false;
          }
        }
        int count=3 + message.entities.size();
        for (int a=0; a < count; a++) {
          boolean allowMin=false;
          if (a != 0) {
            if (a == 1) {
              user_id=message.from_id;
              if (message.post) {
                allowMin=true;
              }
            }
 else             if (a == 2) {
              user_id=message.fwd_from != null ? message.fwd_from.from_id : 0;
            }
 else {
              TLRPC.MessageEntity entity=message.entities.get(a - 3);
              user_id=entity instanceof TLRPC.TL_messageEntityMentionName ? ((TLRPC.TL_messageEntityMentionName)entity).user_id : 0;
            }
          }
          if (user_id > 0) {
            TLRPC.User user=usersDict.get(user_id);
            if (user == null || !allowMin && user.min) {
              user=getUser(user_id);
            }
            if (user == null || !allowMin && user.min) {
              user=MessagesStorage.getInstance(currentAccount).getUserSync(user_id);
              if (user != null && !allowMin && user.min) {
                user=null;
              }
              putUser(user,true);
            }
            if (user == null) {
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("not found user " + user_id);
              }
              return false;
            }
            if (a == 1 && user.status != null && user.status.expires <= 0) {
              onlinePrivacy.put(user_id,ConnectionsManager.getInstance(currentAccount).getCurrentTime());
              interfaceUpdateMask|=UPDATE_MASK_STATUS;
            }
          }
        }
      }
      if (chat != null && chat.megagroup) {
        message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
      }
      if (message.action instanceof TLRPC.TL_messageActionChatDeleteUser) {
        TLRPC.User user=usersDict.get(message.action.user_id);
        if (user != null && user.bot) {
          message.reply_markup=new TLRPC.TL_replyKeyboardHide();
          message.flags|=64;
        }
 else         if (message.from_id == UserConfig.getInstance(currentAccount).getClientUserId() && message.action.user_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
          continue;
        }
      }
      if (messagesArr == null) {
        messagesArr=new ArrayList<>();
      }
      messagesArr.add(message);
      ImageLoader.saveMessageThumbs(message);
      int clientUserId=UserConfig.getInstance(currentAccount).getClientUserId();
      if (message.to_id.chat_id != 0) {
        message.dialog_id=-message.to_id.chat_id;
      }
 else       if (message.to_id.channel_id != 0) {
        message.dialog_id=-message.to_id.channel_id;
      }
 else {
        if (message.to_id.user_id == clientUserId) {
          message.to_id.user_id=message.from_id;
        }
        message.dialog_id=message.to_id.user_id;
      }
      ConcurrentHashMap<Long,Integer> read_max=message.out ? dialogs_read_outbox_max : dialogs_read_inbox_max;
      Integer value=read_max.get(message.dialog_id);
      if (value == null) {
        value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(message.out,message.dialog_id);
        read_max.put(message.dialog_id,value);
      }
      message.unread=!(value >= message.id || chat != null && ChatObject.isNotInChat(chat) || message.action instanceof TLRPC.TL_messageActionChatMigrateTo || message.action instanceof TLRPC.TL_messageActionChannelCreate);
      if (message.dialog_id == clientUserId) {
        message.unread=false;
        message.media_unread=false;
        message.out=true;
      }
      MessageObject obj=new MessageObject(currentAccount,message,usersDict,chatsDict,createdDialogIds.contains(message.dialog_id));
      if (obj.type == 11) {
        interfaceUpdateMask|=UPDATE_MASK_CHAT_AVATAR;
      }
 else       if (obj.type == 10) {
        interfaceUpdateMask|=UPDATE_MASK_CHAT_NAME;
      }
      if (messages == null) {
        messages=new LongSparseArray<>();
      }
      ArrayList<MessageObject> arr=messages.get(message.dialog_id);
      if (arr == null) {
        arr=new ArrayList<>();
        messages.put(message.dialog_id,arr);
      }
      arr.add(obj);
      if (!obj.isOut() && obj.isUnread()) {
        if (pushMessages == null) {
          pushMessages=new ArrayList<>();
        }
        pushMessages.add(obj);
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateReadMessagesContents) {
      TLRPC.TL_updateReadMessagesContents update=(TLRPC.TL_updateReadMessagesContents)baseUpdate;
      if (markAsReadMessages == null) {
        markAsReadMessages=new ArrayList<>();
      }
      for (int a=0, size=update.messages.size(); a < size; a++) {
        long id=update.messages.get(a);
        markAsReadMessages.add(id);
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChannelReadMessagesContents) {
      TLRPC.TL_updateChannelReadMessagesContents update=(TLRPC.TL_updateChannelReadMessagesContents)baseUpdate;
      if (markAsReadMessages == null) {
        markAsReadMessages=new ArrayList<>();
      }
      for (int a=0, size=update.messages.size(); a < size; a++) {
        long id=update.messages.get(a);
        id|=((long)update.channel_id) << 32;
        markAsReadMessages.add(id);
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateReadHistoryInbox) {
      TLRPC.TL_updateReadHistoryInbox update=(TLRPC.TL_updateReadHistoryInbox)baseUpdate;
      long dialog_id;
      if (markAsReadMessagesInbox == null) {
        markAsReadMessagesInbox=new SparseLongArray();
      }
      if (update.peer.chat_id != 0) {
        markAsReadMessagesInbox.put(-update.peer.chat_id,(long)update.max_id);
        dialog_id=-update.peer.chat_id;
      }
 else {
        markAsReadMessagesInbox.put(update.peer.user_id,(long)update.max_id);
        dialog_id=update.peer.user_id;
      }
      Integer value=dialogs_read_inbox_max.get(dialog_id);
      if (value == null) {
        value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(false,dialog_id);
      }
      dialogs_read_inbox_max.put(dialog_id,Math.max(value,update.max_id));
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateReadHistoryOutbox) {
      TLRPC.TL_updateReadHistoryOutbox update=(TLRPC.TL_updateReadHistoryOutbox)baseUpdate;
      long dialog_id;
      if (markAsReadMessagesOutbox == null) {
        markAsReadMessagesOutbox=new SparseLongArray();
      }
      if (update.peer.chat_id != 0) {
        markAsReadMessagesOutbox.put(-update.peer.chat_id,(long)update.max_id);
        dialog_id=-update.peer.chat_id;
      }
 else {
        markAsReadMessagesOutbox.put(update.peer.user_id,(long)update.max_id);
        dialog_id=update.peer.user_id;
      }
      Integer value=dialogs_read_outbox_max.get(dialog_id);
      if (value == null) {
        value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(true,dialog_id);
      }
      dialogs_read_outbox_max.put(dialog_id,Math.max(value,update.max_id));
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateDeleteMessages) {
      TLRPC.TL_updateDeleteMessages update=(TLRPC.TL_updateDeleteMessages)baseUpdate;
      if (deletedMessages == null) {
        deletedMessages=new SparseArray<>();
      }
      ArrayList<Integer> arrayList=deletedMessages.get(0);
      if (arrayList == null) {
        arrayList=new ArrayList<>();
        deletedMessages.put(0,arrayList);
      }
      arrayList.addAll(update.messages);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateUserTyping || baseUpdate instanceof TLRPC.TL_updateChatUserTyping) {
      int user_id;
      int chat_id;
      TLRPC.SendMessageAction action;
      if (baseUpdate instanceof TLRPC.TL_updateUserTyping) {
        TLRPC.TL_updateUserTyping update=(TLRPC.TL_updateUserTyping)baseUpdate;
        user_id=update.user_id;
        action=update.action;
        chat_id=0;
      }
 else {
        TLRPC.TL_updateChatUserTyping update=(TLRPC.TL_updateChatUserTyping)baseUpdate;
        chat_id=update.chat_id;
        user_id=update.user_id;
        action=update.action;
      }
      if (user_id != UserConfig.getInstance(currentAccount).getClientUserId()) {
        long uid=-chat_id;
        if (uid == 0) {
          uid=user_id;
        }
        ArrayList<PrintingUser> arr=printingUsers.get(uid);
        if (action instanceof TLRPC.TL_sendMessageCancelAction) {
          if (arr != null) {
            for (int a=0, size=arr.size(); a < size; a++) {
              PrintingUser pu=arr.get(a);
              if (pu.userId == user_id) {
                arr.remove(a);
                printChanged=true;
                break;
              }
            }
            if (arr.isEmpty()) {
              printingUsers.remove(uid);
            }
          }
        }
 else {
          if (arr == null) {
            arr=new ArrayList<>();
            printingUsers.put(uid,arr);
          }
          boolean exist=false;
          for (          PrintingUser u : arr) {
            if (u.userId == user_id) {
              exist=true;
              u.lastTime=currentTime;
              if (u.action.getClass() != action.getClass()) {
                printChanged=true;
              }
              u.action=action;
              break;
            }
          }
          if (!exist) {
            PrintingUser newUser=new PrintingUser();
            newUser.userId=user_id;
            newUser.lastTime=currentTime;
            newUser.action=action;
            arr.add(newUser);
            printChanged=true;
          }
        }
        onlinePrivacy.put(user_id,ConnectionsManager.getInstance(currentAccount).getCurrentTime());
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChatParticipants) {
      TLRPC.TL_updateChatParticipants update=(TLRPC.TL_updateChatParticipants)baseUpdate;
      interfaceUpdateMask|=UPDATE_MASK_CHAT_MEMBERS;
      if (chatInfoToUpdate == null) {
        chatInfoToUpdate=new ArrayList<>();
      }
      chatInfoToUpdate.add(update.participants);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateUserStatus) {
      interfaceUpdateMask|=UPDATE_MASK_STATUS;
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateUserName) {
      interfaceUpdateMask|=UPDATE_MASK_NAME;
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateUserPhoto) {
      TLRPC.TL_updateUserPhoto update=(TLRPC.TL_updateUserPhoto)baseUpdate;
      interfaceUpdateMask|=UPDATE_MASK_AVATAR;
      MessagesStorage.getInstance(currentAccount).clearUserPhotos(update.user_id);
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateUserPhone) {
      interfaceUpdateMask|=UPDATE_MASK_PHONE;
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateContactLink) {
      TLRPC.TL_updateContactLink update=(TLRPC.TL_updateContactLink)baseUpdate;
      if (contactsIds == null) {
        contactsIds=new ArrayList<>();
      }
      if (update.my_link instanceof TLRPC.TL_contactLinkContact) {
        int idx=contactsIds.indexOf(-update.user_id);
        if (idx != -1) {
          contactsIds.remove(idx);
        }
        if (!contactsIds.contains(update.user_id)) {
          contactsIds.add(update.user_id);
        }
      }
 else {
        int idx=contactsIds.indexOf(update.user_id);
        if (idx != -1) {
          contactsIds.remove(idx);
        }
        if (!contactsIds.contains(update.user_id)) {
          contactsIds.add(-update.user_id);
        }
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateNewEncryptedMessage) {
      ArrayList<TLRPC.Message> decryptedMessages=SecretChatHelper.getInstance(currentAccount).decryptMessage(((TLRPC.TL_updateNewEncryptedMessage)baseUpdate).message);
      if (decryptedMessages != null && !decryptedMessages.isEmpty()) {
        int cid=((TLRPC.TL_updateNewEncryptedMessage)baseUpdate).message.chat_id;
        long uid=((long)cid) << 32;
        if (messages == null) {
          messages=new LongSparseArray<>();
        }
        ArrayList<MessageObject> arr=messages.get(uid);
        if (arr == null) {
          arr=new ArrayList<>();
          messages.put(uid,arr);
        }
        for (int a=0, size=decryptedMessages.size(); a < size; a++) {
          TLRPC.Message message=decryptedMessages.get(a);
          ImageLoader.saveMessageThumbs(message);
          if (messagesArr == null) {
            messagesArr=new ArrayList<>();
          }
          messagesArr.add(message);
          MessageObject obj=new MessageObject(currentAccount,message,usersDict,chatsDict,createdDialogIds.contains(uid));
          arr.add(obj);
          if (pushMessages == null) {
            pushMessages=new ArrayList<>();
          }
          pushMessages.add(obj);
        }
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateEncryptedChatTyping) {
      TLRPC.TL_updateEncryptedChatTyping update=(TLRPC.TL_updateEncryptedChatTyping)baseUpdate;
      TLRPC.EncryptedChat encryptedChat=getEncryptedChatDB(update.chat_id,true);
      if (encryptedChat != null) {
        long uid=((long)update.chat_id) << 32;
        ArrayList<PrintingUser> arr=printingUsers.get(uid);
        if (arr == null) {
          arr=new ArrayList<>();
          printingUsers.put(uid,arr);
        }
        boolean exist=false;
        for (int a=0, size=arr.size(); a < size; a++) {
          PrintingUser u=arr.get(a);
          if (u.userId == encryptedChat.user_id) {
            exist=true;
            u.lastTime=currentTime;
            u.action=new TLRPC.TL_sendMessageTypingAction();
            break;
          }
        }
        if (!exist) {
          PrintingUser newUser=new PrintingUser();
          newUser.userId=encryptedChat.user_id;
          newUser.lastTime=currentTime;
          newUser.action=new TLRPC.TL_sendMessageTypingAction();
          arr.add(newUser);
          printChanged=true;
        }
        onlinePrivacy.put(encryptedChat.user_id,ConnectionsManager.getInstance(currentAccount).getCurrentTime());
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateEncryptedMessagesRead) {
      TLRPC.TL_updateEncryptedMessagesRead update=(TLRPC.TL_updateEncryptedMessagesRead)baseUpdate;
      if (markAsReadEncrypted == null) {
        markAsReadEncrypted=new SparseIntArray();
      }
      markAsReadEncrypted.put(update.chat_id,update.max_date);
      if (tasks == null) {
        tasks=new ArrayList<>();
      }
      tasks.add((TLRPC.TL_updateEncryptedMessagesRead)baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChatParticipantAdd) {
      TLRPC.TL_updateChatParticipantAdd update=(TLRPC.TL_updateChatParticipantAdd)baseUpdate;
      MessagesStorage.getInstance(currentAccount).updateChatInfo(update.chat_id,update.user_id,0,update.inviter_id,update.version);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChatParticipantDelete) {
      TLRPC.TL_updateChatParticipantDelete update=(TLRPC.TL_updateChatParticipantDelete)baseUpdate;
      MessagesStorage.getInstance(currentAccount).updateChatInfo(update.chat_id,update.user_id,1,0,update.version);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateDcOptions || baseUpdate instanceof TLRPC.TL_updateConfig) {
      ConnectionsManager.getInstance(currentAccount).updateDcSettings();
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateEncryption) {
      SecretChatHelper.getInstance(currentAccount).processUpdateEncryption((TLRPC.TL_updateEncryption)baseUpdate,usersDict);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateUserBlocked) {
      final TLRPC.TL_updateUserBlocked finalUpdate=(TLRPC.TL_updateUserBlocked)baseUpdate;
      if (finalUpdate.blocked) {
        SparseIntArray ids=new SparseIntArray();
        ids.put(finalUpdate.user_id,1);
        MessagesStorage.getInstance(currentAccount).putBlockedUsers(ids,false);
      }
 else {
        MessagesStorage.getInstance(currentAccount).deleteBlockedUser(finalUpdate.user_id);
      }
      MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> {
        if (finalUpdate.blocked) {
          if (blockedUsers.indexOfKey(finalUpdate.user_id) < 0) {
            blockedUsers.put(finalUpdate.user_id,1);
          }
        }
 else {
          blockedUsers.delete(finalUpdate.user_id);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.blockedUsersDidLoad);
      }
));
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateNotifySettings) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateServiceNotification) {
      final TLRPC.TL_updateServiceNotification update=(TLRPC.TL_updateServiceNotification)baseUpdate;
      if (update.popup && update.message != null && update.message.length() > 0) {
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needShowAlert,2,update.message,update.type));
      }
      if ((update.flags & 2) != 0) {
        TLRPC.TL_message newMessage=new TLRPC.TL_message();
        newMessage.local_id=newMessage.id=UserConfig.getInstance(currentAccount).getNewMessageId();
        UserConfig.getInstance(currentAccount).saveConfig(false);
        newMessage.unread=true;
        newMessage.flags=TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
        if (update.inbox_date != 0) {
          newMessage.date=update.inbox_date;
        }
 else {
          newMessage.date=(int)(System.currentTimeMillis() / 1000);
        }
        newMessage.from_id=777000;
        newMessage.to_id=new TLRPC.TL_peerUser();
        newMessage.to_id.user_id=UserConfig.getInstance(currentAccount).getClientUserId();
        newMessage.dialog_id=777000;
        if (update.media != null) {
          newMessage.media=update.media;
          newMessage.flags|=TLRPC.MESSAGE_FLAG_HAS_MEDIA;
        }
        newMessage.message=update.message;
        if (update.entities != null) {
          newMessage.entities=update.entities;
        }
        if (messagesArr == null) {
          messagesArr=new ArrayList<>();
        }
        messagesArr.add(newMessage);
        MessageObject obj=new MessageObject(currentAccount,newMessage,usersDict,chatsDict,createdDialogIds.contains(newMessage.dialog_id));
        if (messages == null) {
          messages=new LongSparseArray<>();
        }
        ArrayList<MessageObject> arr=messages.get(newMessage.dialog_id);
        if (arr == null) {
          arr=new ArrayList<>();
          messages.put(newMessage.dialog_id,arr);
        }
        arr.add(obj);
        if (pushMessages == null) {
          pushMessages=new ArrayList<>();
        }
        pushMessages.add(obj);
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateDialogPinned) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updatePinnedDialogs) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateFolderPeers) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
      TLRPC.TL_updateFolderPeers update=(TLRPC.TL_updateFolderPeers)baseUpdate;
      MessagesStorage.getInstance(currentAccount).setDialogsFolderId(update.folder_peers,null,0,0);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updatePrivacy) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateWebPage) {
      TLRPC.TL_updateWebPage update=(TLRPC.TL_updateWebPage)baseUpdate;
      if (webPages == null) {
        webPages=new LongSparseArray<>();
      }
      webPages.put(update.webpage.id,update.webpage);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChannelWebPage) {
      TLRPC.TL_updateChannelWebPage update=(TLRPC.TL_updateChannelWebPage)baseUpdate;
      if (webPages == null) {
        webPages=new LongSparseArray<>();
      }
      webPages.put(update.webpage.id,update.webpage);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChannelTooLong) {
      TLRPC.TL_updateChannelTooLong update=(TLRPC.TL_updateChannelTooLong)baseUpdate;
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d(baseUpdate + " channelId = " + update.channel_id);
      }
      int channelPts=channelsPts.get(update.channel_id);
      if (channelPts == 0) {
        channelPts=MessagesStorage.getInstance(currentAccount).getChannelPtsSync(update.channel_id);
        if (channelPts == 0) {
          TLRPC.Chat chat=chatsDict.get(update.channel_id);
          if (chat == null || chat.min) {
            chat=getChat(update.channel_id);
          }
          if (chat == null || chat.min) {
            chat=MessagesStorage.getInstance(currentAccount).getChatSync(update.channel_id);
            putChat(chat,true);
          }
          if (chat != null && !chat.min) {
            loadUnknownChannel(chat,0);
          }
        }
 else {
          channelsPts.put(update.channel_id,channelPts);
        }
      }
      if (channelPts != 0) {
        if ((update.flags & 1) != 0) {
          if (update.pts > channelPts) {
            getChannelDifference(update.channel_id);
          }
        }
 else {
          getChannelDifference(update.channel_id);
        }
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateReadChannelInbox) {
      TLRPC.TL_updateReadChannelInbox update=(TLRPC.TL_updateReadChannelInbox)baseUpdate;
      long message_id=update.max_id;
      message_id|=((long)update.channel_id) << 32;
      long dialog_id=-update.channel_id;
      if (markAsReadMessagesInbox == null) {
        markAsReadMessagesInbox=new SparseLongArray();
      }
      markAsReadMessagesInbox.put(-update.channel_id,message_id);
      Integer value=dialogs_read_inbox_max.get(dialog_id);
      if (value == null) {
        value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(false,dialog_id);
      }
      dialogs_read_inbox_max.put(dialog_id,Math.max(value,update.max_id));
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateReadChannelOutbox) {
      TLRPC.TL_updateReadChannelOutbox update=(TLRPC.TL_updateReadChannelOutbox)baseUpdate;
      long message_id=update.max_id;
      message_id|=((long)update.channel_id) << 32;
      long dialog_id=-update.channel_id;
      if (markAsReadMessagesOutbox == null) {
        markAsReadMessagesOutbox=new SparseLongArray();
      }
      markAsReadMessagesOutbox.put(-update.channel_id,message_id);
      Integer value=dialogs_read_outbox_max.get(dialog_id);
      if (value == null) {
        value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(true,dialog_id);
      }
      dialogs_read_outbox_max.put(dialog_id,Math.max(value,update.max_id));
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateDeleteChannelMessages) {
      TLRPC.TL_updateDeleteChannelMessages update=(TLRPC.TL_updateDeleteChannelMessages)baseUpdate;
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d(baseUpdate + " channelId = " + update.channel_id);
      }
      if (deletedMessages == null) {
        deletedMessages=new SparseArray<>();
      }
      ArrayList<Integer> arrayList=deletedMessages.get(update.channel_id);
      if (arrayList == null) {
        arrayList=new ArrayList<>();
        deletedMessages.put(update.channel_id,arrayList);
      }
      arrayList.addAll(update.messages);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChannel) {
      if (BuildVars.LOGS_ENABLED) {
        TLRPC.TL_updateChannel update=(TLRPC.TL_updateChannel)baseUpdate;
        FileLog.d(baseUpdate + " channelId = " + update.channel_id);
      }
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChannelMessageViews) {
      TLRPC.TL_updateChannelMessageViews update=(TLRPC.TL_updateChannelMessageViews)baseUpdate;
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d(baseUpdate + " channelId = " + update.channel_id);
      }
      if (channelViews == null) {
        channelViews=new SparseArray<>();
      }
      SparseIntArray array=channelViews.get(update.channel_id);
      if (array == null) {
        array=new SparseIntArray();
        channelViews.put(update.channel_id,array);
      }
      array.put(update.id,update.views);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChatParticipantAdmin) {
      TLRPC.TL_updateChatParticipantAdmin update=(TLRPC.TL_updateChatParticipantAdmin)baseUpdate;
      MessagesStorage.getInstance(currentAccount).updateChatInfo(update.chat_id,update.user_id,2,update.is_admin ? 1 : 0,update.version);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChatDefaultBannedRights) {
      TLRPC.TL_updateChatDefaultBannedRights update=(TLRPC.TL_updateChatDefaultBannedRights)baseUpdate;
      int chatId;
      if (update.peer.channel_id != 0) {
        chatId=update.peer.channel_id;
      }
 else {
        chatId=update.peer.chat_id;
      }
      MessagesStorage.getInstance(currentAccount).updateChatDefaultBannedRights(chatId,update.default_banned_rights,update.version);
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateStickerSets) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateStickerSetsOrder) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateNewStickerSet) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateDraftMessage) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateSavedGifs) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateEditChannelMessage || baseUpdate instanceof TLRPC.TL_updateEditMessage) {
      TLRPC.Message message;
      int clientUserId=UserConfig.getInstance(currentAccount).getClientUserId();
      if (baseUpdate instanceof TLRPC.TL_updateEditChannelMessage) {
        message=((TLRPC.TL_updateEditChannelMessage)baseUpdate).message;
        TLRPC.Chat chat=chatsDict.get(message.to_id.channel_id);
        if (chat == null) {
          chat=getChat(message.to_id.channel_id);
        }
        if (chat == null) {
          chat=MessagesStorage.getInstance(currentAccount).getChatSync(message.to_id.channel_id);
          putChat(chat,true);
        }
        if (chat != null && chat.megagroup) {
          message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
        }
      }
 else {
        message=((TLRPC.TL_updateEditMessage)baseUpdate).message;
        if (message.dialog_id == clientUserId) {
          message.unread=false;
          message.media_unread=false;
          message.out=true;
        }
      }
      if (!message.out && message.from_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
        message.out=true;
      }
      if (!fromGetDifference) {
        for (int a=0, count=message.entities.size(); a < count; a++) {
          TLRPC.MessageEntity entity=message.entities.get(a);
          if (entity instanceof TLRPC.TL_messageEntityMentionName) {
            int user_id=((TLRPC.TL_messageEntityMentionName)entity).user_id;
            TLRPC.User user=usersDict.get(user_id);
            if (user == null || user.min) {
              user=getUser(user_id);
            }
            if (user == null || user.min) {
              user=MessagesStorage.getInstance(currentAccount).getUserSync(user_id);
              if (user != null && user.min) {
                user=null;
              }
              putUser(user,true);
            }
            if (user == null) {
              return false;
            }
          }
        }
      }
      if (message.to_id.chat_id != 0) {
        message.dialog_id=-message.to_id.chat_id;
      }
 else       if (message.to_id.channel_id != 0) {
        message.dialog_id=-message.to_id.channel_id;
      }
 else {
        if (message.to_id.user_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
          message.to_id.user_id=message.from_id;
        }
        message.dialog_id=message.to_id.user_id;
      }
      ConcurrentHashMap<Long,Integer> read_max=message.out ? dialogs_read_outbox_max : dialogs_read_inbox_max;
      Integer value=read_max.get(message.dialog_id);
      if (value == null) {
        value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(message.out,message.dialog_id);
        read_max.put(message.dialog_id,value);
      }
      message.unread=value < message.id;
      if (message.dialog_id == clientUserId) {
        message.out=true;
        message.unread=false;
        message.media_unread=false;
      }
      if (message.out && message.message == null) {
        message.message="";
        message.attachPath="";
      }
      ImageLoader.saveMessageThumbs(message);
      MessageObject obj=new MessageObject(currentAccount,message,usersDict,chatsDict,createdDialogIds.contains(message.dialog_id));
      if (editingMessages == null) {
        editingMessages=new LongSparseArray<>();
      }
      ArrayList<MessageObject> arr=editingMessages.get(message.dialog_id);
      if (arr == null) {
        arr=new ArrayList<>();
        editingMessages.put(message.dialog_id,arr);
      }
      arr.add(obj);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChannelPinnedMessage) {
      TLRPC.TL_updateChannelPinnedMessage update=(TLRPC.TL_updateChannelPinnedMessage)baseUpdate;
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d(baseUpdate + " channelId = " + update.channel_id);
      }
      MessagesStorage.getInstance(currentAccount).updateChatPinnedMessage(update.channel_id,update.id);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChatPinnedMessage) {
      TLRPC.TL_updateChatPinnedMessage update=(TLRPC.TL_updateChatPinnedMessage)baseUpdate;
      MessagesStorage.getInstance(currentAccount).updateChatPinnedMessage(update.chat_id,update.id);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateUserPinnedMessage) {
      TLRPC.TL_updateUserPinnedMessage update=(TLRPC.TL_updateUserPinnedMessage)baseUpdate;
      MessagesStorage.getInstance(currentAccount).updateUserPinnedMessage(update.user_id,update.id);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateReadFeaturedStickers) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updatePhoneCall) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateLangPack) {
      TLRPC.TL_updateLangPack update=(TLRPC.TL_updateLangPack)baseUpdate;
      AndroidUtilities.runOnUIThread(() -> LocaleController.getInstance().saveRemoteLocaleStringsForCurrentLocale(update.difference,currentAccount));
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateLangPackTooLong) {
      TLRPC.TL_updateLangPackTooLong update=(TLRPC.TL_updateLangPackTooLong)baseUpdate;
      LocaleController.getInstance().reloadCurrentRemoteLocale(currentAccount,update.lang_code);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateFavedStickers) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateContactsReset) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateChannelAvailableMessages) {
      TLRPC.TL_updateChannelAvailableMessages update=(TLRPC.TL_updateChannelAvailableMessages)baseUpdate;
      if (clearHistoryMessages == null) {
        clearHistoryMessages=new SparseIntArray();
      }
      int currentValue=clearHistoryMessages.get(update.channel_id);
      if (currentValue == 0 || currentValue < update.available_min_id) {
        clearHistoryMessages.put(update.channel_id,update.available_min_id);
      }
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateDialogUnreadMark) {
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
 else     if (baseUpdate instanceof TLRPC.TL_updateMessagePoll) {
      TLRPC.TL_updateMessagePoll update=(TLRPC.TL_updateMessagePoll)baseUpdate;
      long time=SendMessagesHelper.getInstance(currentAccount).getVoteSendTime(update.poll_id);
      if (Math.abs(SystemClock.uptimeMillis() - time) < 600) {
        continue;
      }
      MessagesStorage.getInstance(currentAccount).updateMessagePollResults(update.poll_id,update.poll,update.results);
      if (updatesOnMainThread == null) {
        updatesOnMainThread=new ArrayList<>();
      }
      updatesOnMainThread.add(baseUpdate);
    }
  }
  if (messages != null) {
    for (int a=0, size=messages.size(); a < size; a++) {
      long key=messages.keyAt(a);
      ArrayList<MessageObject> value=messages.valueAt(a);
      if (updatePrintingUsersWithNewMessages(key,value)) {
        printChanged=true;
      }
    }
  }
  if (printChanged) {
    updatePrintingStrings();
  }
  final int interfaceUpdateMaskFinal=interfaceUpdateMask;
  final boolean printChangedArg=printChanged;
  if (contactsIds != null) {
    ContactsController.getInstance(currentAccount).processContactsUpdates(contactsIds,usersDict);
  }
  if (pushMessages != null) {
    final ArrayList<MessageObject> pushMessagesFinal=pushMessages;
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> NotificationsController.getInstance(currentAccount).processNewMessages(pushMessagesFinal,true,false,null)));
  }
  if (messagesArr != null) {
    StatsController.getInstance(currentAccount).incrementReceivedItemsCount(ApplicationLoader.getCurrentNetworkType(),StatsController.TYPE_MESSAGES,messagesArr.size());
    MessagesStorage.getInstance(currentAccount).putMessages(messagesArr,true,true,false,DownloadController.getInstance(currentAccount).getAutodownloadMask());
  }
  if (editingMessages != null) {
    for (int b=0, size=editingMessages.size(); b < size; b++) {
      TLRPC.TL_messages_messages messagesRes=new TLRPC.TL_messages_messages();
      ArrayList<MessageObject> messageObjects=editingMessages.valueAt(b);
      for (int a=0, size2=messageObjects.size(); a < size2; a++) {
        messagesRes.messages.add(messageObjects.get(a).messageOwner);
      }
      MessagesStorage.getInstance(currentAccount).putMessages(messagesRes,editingMessages.keyAt(b),-2,0,false);
    }
  }
  if (channelViews != null) {
    MessagesStorage.getInstance(currentAccount).putChannelViews(channelViews,true);
  }
  final LongSparseArray<ArrayList<MessageObject>> editingMessagesFinal=editingMessages;
  final SparseArray<SparseIntArray> channelViewsFinal=channelViews;
  final LongSparseArray<TLRPC.WebPage> webPagesFinal=webPages;
  final LongSparseArray<ArrayList<MessageObject>> messagesFinal=messages;
  final ArrayList<TLRPC.ChatParticipants> chatInfoToUpdateFinal=chatInfoToUpdate;
  final ArrayList<Integer> contactsIdsFinal=contactsIds;
  final ArrayList<TLRPC.Update> updatesOnMainThreadFinal=updatesOnMainThread;
  AndroidUtilities.runOnUIThread(() -> {
    int updateMask=interfaceUpdateMaskFinal;
    boolean forceDialogsUpdate=false;
    if (updatesOnMainThreadFinal != null) {
      ArrayList<TLRPC.User> dbUsers=new ArrayList<>();
      ArrayList<TLRPC.User> dbUsersStatus=new ArrayList<>();
      SharedPreferences.Editor editor=null;
      for (int a=0, size=updatesOnMainThreadFinal.size(); a < size; a++) {
        final TLRPC.Update baseUpdate=updatesOnMainThreadFinal.get(a);
        if (baseUpdate instanceof TLRPC.TL_updatePrivacy) {
          TLRPC.TL_updatePrivacy update=(TLRPC.TL_updatePrivacy)baseUpdate;
          if (update.key instanceof TLRPC.TL_privacyKeyStatusTimestamp) {
            ContactsController.getInstance(currentAccount).setPrivacyRules(update.rules,0);
          }
 else           if (update.key instanceof TLRPC.TL_privacyKeyChatInvite) {
            ContactsController.getInstance(currentAccount).setPrivacyRules(update.rules,1);
          }
 else           if (update.key instanceof TLRPC.TL_privacyKeyPhoneCall) {
            ContactsController.getInstance(currentAccount).setPrivacyRules(update.rules,2);
          }
 else           if (update.key instanceof TLRPC.TL_privacyKeyPhoneP2P) {
            ContactsController.getInstance(currentAccount).setPrivacyRules(update.rules,3);
          }
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateUserStatus) {
          TLRPC.TL_updateUserStatus update=(TLRPC.TL_updateUserStatus)baseUpdate;
          final TLRPC.User currentUser=getUser(update.user_id);
          if (update.status instanceof TLRPC.TL_userStatusRecently) {
            update.status.expires=-100;
          }
 else           if (update.status instanceof TLRPC.TL_userStatusLastWeek) {
            update.status.expires=-101;
          }
 else           if (update.status instanceof TLRPC.TL_userStatusLastMonth) {
            update.status.expires=-102;
          }
          if (currentUser != null) {
            currentUser.id=update.user_id;
            currentUser.status=update.status;
          }
          final TLRPC.User toDbUser=new TLRPC.TL_user();
          toDbUser.id=update.user_id;
          toDbUser.status=update.status;
          dbUsersStatus.add(toDbUser);
          if (update.user_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
            NotificationsController.getInstance(currentAccount).setLastOnlineFromOtherDevice(update.status.expires);
          }
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateUserName) {
          TLRPC.TL_updateUserName update=(TLRPC.TL_updateUserName)baseUpdate;
          final TLRPC.User currentUser=getUser(update.user_id);
          if (currentUser != null) {
            if (!UserObject.isContact(currentUser)) {
              currentUser.first_name=update.first_name;
              currentUser.last_name=update.last_name;
            }
            if (!TextUtils.isEmpty(currentUser.username)) {
              objectsByUsernames.remove(currentUser.username);
            }
            if (TextUtils.isEmpty(update.username)) {
              objectsByUsernames.put(update.username,currentUser);
            }
            currentUser.username=update.username;
          }
          final TLRPC.User toDbUser=new TLRPC.TL_user();
          toDbUser.id=update.user_id;
          toDbUser.first_name=update.first_name;
          toDbUser.last_name=update.last_name;
          toDbUser.username=update.username;
          dbUsers.add(toDbUser);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateDialogPinned) {
          TLRPC.TL_updateDialogPinned update=(TLRPC.TL_updateDialogPinned)baseUpdate;
          long did;
          if (update.peer instanceof TLRPC.TL_dialogPeer) {
            TLRPC.TL_dialogPeer dialogPeer=(TLRPC.TL_dialogPeer)update.peer;
            did=DialogObject.getPeerDialogId(dialogPeer.peer);
          }
 else {
            did=0;
          }
          if (!pinDialog(did,update.pinned,null,-1)) {
            UserConfig.getInstance(currentAccount).setPinnedDialogsLoaded(update.folder_id,false);
            UserConfig.getInstance(currentAccount).saveConfig(false);
            loadPinnedDialogs(update.folder_id,did,null);
          }
        }
 else         if (baseUpdate instanceof TLRPC.TL_updatePinnedDialogs) {
          TLRPC.TL_updatePinnedDialogs update=(TLRPC.TL_updatePinnedDialogs)baseUpdate;
          UserConfig.getInstance(currentAccount).setPinnedDialogsLoaded(update.folder_id,false);
          UserConfig.getInstance(currentAccount).saveConfig(false);
          ArrayList<Long> order;
          if ((update.flags & 1) != 0) {
            order=new ArrayList<>();
            ArrayList<TLRPC.DialogPeer> peers=update.order;
            for (int b=0, size2=peers.size(); b < size2; b++) {
              long did;
              TLRPC.DialogPeer dialogPeer=peers.get(b);
              if (dialogPeer instanceof TLRPC.TL_dialogPeer) {
                TLRPC.Peer peer=((TLRPC.TL_dialogPeer)dialogPeer).peer;
                if (peer.user_id != 0) {
                  did=peer.user_id;
                }
 else                 if (peer.chat_id != 0) {
                  did=-peer.chat_id;
                }
 else {
                  did=-peer.channel_id;
                }
              }
 else {
                did=0;
              }
              order.add(did);
            }
          }
 else {
            order=null;
          }
          loadPinnedDialogs(update.folder_id,0,order);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateFolderPeers) {
          TLRPC.TL_updateFolderPeers update=(TLRPC.TL_updateFolderPeers)baseUpdate;
          for (int b=0, size2=update.folder_peers.size(); b < size2; b++) {
            TLRPC.TL_folderPeer folderPeer=update.folder_peers.get(b);
            long dialogId=DialogObject.getPeerDialogId(folderPeer.peer);
            TLRPC.Dialog dialog=dialogs_dict.get(dialogId);
            if (dialog == null) {
              continue;
            }
            dialog.folder_id=folderPeer.folder_id;
            ensureFolderDialogExists(folderPeer.folder_id,null);
          }
          forceDialogsUpdate=true;
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateUserPhoto) {
          TLRPC.TL_updateUserPhoto update=(TLRPC.TL_updateUserPhoto)baseUpdate;
          final TLRPC.User currentUser=getUser(update.user_id);
          if (currentUser != null) {
            currentUser.photo=update.photo;
          }
          final TLRPC.User toDbUser=new TLRPC.TL_user();
          toDbUser.id=update.user_id;
          toDbUser.photo=update.photo;
          dbUsers.add(toDbUser);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateUserPhone) {
          TLRPC.TL_updateUserPhone update=(TLRPC.TL_updateUserPhone)baseUpdate;
          final TLRPC.User currentUser=getUser(update.user_id);
          if (currentUser != null) {
            currentUser.phone=update.phone;
            Utilities.phoneBookQueue.postRunnable(() -> ContactsController.getInstance(currentAccount).addContactToPhoneBook(currentUser,true));
          }
          final TLRPC.User toDbUser=new TLRPC.TL_user();
          toDbUser.id=update.user_id;
          toDbUser.phone=update.phone;
          dbUsers.add(toDbUser);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateNotifySettings) {
          TLRPC.TL_updateNotifySettings update=(TLRPC.TL_updateNotifySettings)baseUpdate;
          if (update.notify_settings instanceof TLRPC.TL_peerNotifySettings) {
            if (editor == null) {
              editor=notificationsPreferences.edit();
            }
            int currentTime1=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
            if (update.peer instanceof TLRPC.TL_notifyPeer) {
              TLRPC.TL_notifyPeer notifyPeer=(TLRPC.TL_notifyPeer)update.peer;
              long dialog_id;
              if (notifyPeer.peer.user_id != 0) {
                dialog_id=notifyPeer.peer.user_id;
              }
 else               if (notifyPeer.peer.chat_id != 0) {
                dialog_id=-notifyPeer.peer.chat_id;
              }
 else {
                dialog_id=-notifyPeer.peer.channel_id;
              }
              TLRPC.Dialog dialog=dialogs_dict.get(dialog_id);
              if (dialog != null) {
                dialog.notify_settings=update.notify_settings;
              }
              if ((update.notify_settings.flags & 2) != 0) {
                editor.putBoolean("silent_" + dialog_id,update.notify_settings.silent);
              }
 else {
                editor.remove("silent_" + dialog_id);
              }
              if ((update.notify_settings.flags & 4) != 0) {
                if (update.notify_settings.mute_until > currentTime1) {
                  int until=0;
                  if (update.notify_settings.mute_until > currentTime1 + 60 * 60 * 24 * 365) {
                    editor.putInt("notify2_" + dialog_id,2);
                    if (dialog != null) {
                      update.notify_settings.mute_until=Integer.MAX_VALUE;
                    }
                  }
 else {
                    until=update.notify_settings.mute_until;
                    editor.putInt("notify2_" + dialog_id,3);
                    editor.putInt("notifyuntil_" + dialog_id,update.notify_settings.mute_until);
                    if (dialog != null) {
                      update.notify_settings.mute_until=until;
                    }
                  }
                  MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,((long)until << 32) | 1);
                  NotificationsController.getInstance(currentAccount).removeNotificationsForDialog(dialog_id);
                }
 else {
                  if (dialog != null) {
                    update.notify_settings.mute_until=0;
                  }
                  editor.putInt("notify2_" + dialog_id,0);
                  MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,0);
                }
              }
 else {
                if (dialog != null) {
                  update.notify_settings.mute_until=0;
                }
                editor.remove("notify2_" + dialog_id);
                MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,0);
              }
            }
 else             if (update.peer instanceof TLRPC.TL_notifyChats) {
              if ((update.notify_settings.flags & 1) != 0) {
                editor.putBoolean("EnablePreviewGroup",update.notify_settings.show_previews);
              }
              if ((update.notify_settings.flags & 2) != 0) {
              }
              if ((update.notify_settings.flags & 4) != 0) {
                editor.putInt("EnableGroup2",update.notify_settings.mute_until);
              }
            }
 else             if (update.peer instanceof TLRPC.TL_notifyUsers) {
              if ((update.notify_settings.flags & 1) != 0) {
                editor.putBoolean("EnablePreviewAll",update.notify_settings.show_previews);
              }
              if ((update.notify_settings.flags & 2) != 0) {
              }
              if ((update.notify_settings.flags & 4) != 0) {
                editor.putInt("EnableAll2",update.notify_settings.mute_until);
              }
            }
 else             if (update.peer instanceof TLRPC.TL_notifyBroadcasts) {
              if ((update.notify_settings.flags & 1) != 0) {
                editor.putBoolean("EnablePreviewChannel",update.notify_settings.show_previews);
              }
              if ((update.notify_settings.flags & 2) != 0) {
              }
              if ((update.notify_settings.flags & 4) != 0) {
                editor.putInt("EnableChannel2",update.notify_settings.mute_until);
              }
            }
          }
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateChannel) {
          final TLRPC.TL_updateChannel update=(TLRPC.TL_updateChannel)baseUpdate;
          TLRPC.Dialog dialog=dialogs_dict.get(-(long)update.channel_id);
          TLRPC.Chat chat=getChat(update.channel_id);
          if (chat != null) {
            if (dialog == null && chat instanceof TLRPC.TL_channel && !chat.left) {
              Utilities.stageQueue.postRunnable(() -> getChannelDifference(update.channel_id,1,0,null));
            }
 else             if (chat.left && dialog != null && (proxyDialog == null || proxyDialog.id != dialog.id)) {
              deleteDialog(dialog.id,0);
            }
          }
          updateMask|=UPDATE_MASK_CHAT;
          loadFullChat(update.channel_id,0,true);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateChatDefaultBannedRights) {
          TLRPC.TL_updateChatDefaultBannedRights update=(TLRPC.TL_updateChatDefaultBannedRights)baseUpdate;
          int chatId;
          if (update.peer.channel_id != 0) {
            chatId=update.peer.channel_id;
          }
 else {
            chatId=update.peer.chat_id;
          }
          TLRPC.Chat chat=getChat(chatId);
          if (chat != null) {
            chat.default_banned_rights=update.default_banned_rights;
            AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.channelRightsUpdated,chat));
          }
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateStickerSets) {
          TLRPC.TL_updateStickerSets update=(TLRPC.TL_updateStickerSets)baseUpdate;
          DataQuery.getInstance(currentAccount).loadStickers(DataQuery.TYPE_IMAGE,false,true);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateStickerSetsOrder) {
          TLRPC.TL_updateStickerSetsOrder update=(TLRPC.TL_updateStickerSetsOrder)baseUpdate;
          DataQuery.getInstance(currentAccount).reorderStickers(update.masks ? DataQuery.TYPE_MASK : DataQuery.TYPE_IMAGE,((TLRPC.TL_updateStickerSetsOrder)baseUpdate).order);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateFavedStickers) {
          DataQuery.getInstance(currentAccount).loadRecents(DataQuery.TYPE_FAVE,false,false,true);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateContactsReset) {
          ContactsController.getInstance(currentAccount).forceImportContacts();
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateNewStickerSet) {
          TLRPC.TL_updateNewStickerSet update=(TLRPC.TL_updateNewStickerSet)baseUpdate;
          DataQuery.getInstance(currentAccount).addNewStickerSet(update.stickerset);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateSavedGifs) {
          SharedPreferences.Editor editor2=emojiPreferences.edit();
          editor2.putLong("lastGifLoadTime",0).commit();
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateRecentStickers) {
          SharedPreferences.Editor editor2=emojiPreferences.edit();
          editor2.putLong("lastStickersLoadTime",0).commit();
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateDraftMessage) {
          TLRPC.TL_updateDraftMessage update=(TLRPC.TL_updateDraftMessage)baseUpdate;
          forceDialogsUpdate=true;
          long did;
          TLRPC.Peer peer=((TLRPC.TL_updateDraftMessage)baseUpdate).peer;
          if (peer.user_id != 0) {
            did=peer.user_id;
          }
 else           if (peer.channel_id != 0) {
            did=-peer.channel_id;
          }
 else {
            did=-peer.chat_id;
          }
          DataQuery.getInstance(currentAccount).saveDraft(did,update.draft,null,true);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateReadFeaturedStickers) {
          DataQuery.getInstance(currentAccount).markFaturedStickersAsRead(false);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updatePhoneCall) {
          TLRPC.TL_updatePhoneCall upd=(TLRPC.TL_updatePhoneCall)baseUpdate;
          TLRPC.PhoneCall call=upd.phone_call;
          VoIPService svc=VoIPService.getSharedInstance();
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("Received call in update: " + call);
            FileLog.d("call id " + call.id);
          }
          if (call instanceof TLRPC.TL_phoneCallRequested) {
            if (call.date + callRingTimeout / 1000 < ConnectionsManager.getInstance(currentAccount).getCurrentTime()) {
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("ignoring too old call");
              }
              continue;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !NotificationManagerCompat.from(ApplicationLoader.applicationContext).areNotificationsEnabled()) {
              if (BuildVars.LOGS_ENABLED)               FileLog.d("Ignoring incoming call because notifications are disabled in system");
              continue;
            }
            TelephonyManager tm=(TelephonyManager)ApplicationLoader.applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
            if (svc != null || VoIPService.callIShouldHavePutIntoIntent != null || tm.getCallState() != TelephonyManager.CALL_STATE_IDLE) {
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("Auto-declining call " + call.id + " because there's already active one");
              }
              TLRPC.TL_phone_discardCall req=new TLRPC.TL_phone_discardCall();
              req.peer=new TLRPC.TL_inputPhoneCall();
              req.peer.access_hash=call.access_hash;
              req.peer.id=call.id;
              req.reason=new TLRPC.TL_phoneCallDiscardReasonBusy();
              ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
                if (response != null) {
                  TLRPC.Updates updates1=(TLRPC.Updates)response;
                  processUpdates(updates1,false);
                }
              }
);
              continue;
            }
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("Starting service for call " + call.id);
            }
            VoIPService.callIShouldHavePutIntoIntent=call;
            Intent intent=new Intent(ApplicationLoader.applicationContext,VoIPService.class);
            intent.putExtra("is_outgoing",false);
            intent.putExtra("user_id",call.participant_id == UserConfig.getInstance(currentAccount).getClientUserId() ? call.admin_id : call.participant_id);
            intent.putExtra("account",currentAccount);
            try {
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ApplicationLoader.applicationContext.startForegroundService(intent);
              }
 else {
                ApplicationLoader.applicationContext.startService(intent);
              }
            }
 catch (            Throwable e) {
              FileLog.e(e);
            }
          }
 else {
            if (svc != null && call != null) {
              svc.onCallUpdated(call);
            }
 else             if (VoIPService.callIShouldHavePutIntoIntent != null) {
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("Updated the call while the service is starting");
              }
              if (call.id == VoIPService.callIShouldHavePutIntoIntent.id) {
                VoIPService.callIShouldHavePutIntoIntent=call;
              }
            }
          }
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateDialogUnreadMark) {
          TLRPC.TL_updateDialogUnreadMark update=(TLRPC.TL_updateDialogUnreadMark)baseUpdate;
          long did;
          if (update.peer instanceof TLRPC.TL_dialogPeer) {
            TLRPC.TL_dialogPeer dialogPeer=(TLRPC.TL_dialogPeer)update.peer;
            if (dialogPeer.peer.user_id != 0) {
              did=dialogPeer.peer.user_id;
            }
 else             if (dialogPeer.peer.chat_id != 0) {
              did=-dialogPeer.peer.chat_id;
            }
 else {
              did=-dialogPeer.peer.channel_id;
            }
          }
 else {
            did=0;
          }
          MessagesStorage.getInstance(currentAccount).setDialogUnread(did,update.unread);
          TLRPC.Dialog dialog=dialogs_dict.get(did);
          if (dialog != null && dialog.unread_mark != update.unread) {
            dialog.unread_mark=update.unread;
            if (dialog.unread_count == 0 && !isDialogMuted(did)) {
              if (dialog.unread_mark) {
                unreadUnmutedDialogs++;
              }
 else {
                unreadUnmutedDialogs--;
              }
            }
            updateMask|=UPDATE_MASK_READ_DIALOG_MESSAGE;
          }
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateMessagePoll) {
          TLRPC.TL_updateMessagePoll update=(TLRPC.TL_updateMessagePoll)baseUpdate;
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didUpdatePollResults,update.poll_id,update.poll,update.results);
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateGroupCall) {
        }
 else         if (baseUpdate instanceof TLRPC.TL_updateGroupCallParticipant) {
        }
      }
      if (editor != null) {
        editor.commit();
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.notificationsSettingsUpdated);
      }
      MessagesStorage.getInstance(currentAccount).updateUsers(dbUsersStatus,true,true,true);
      MessagesStorage.getInstance(currentAccount).updateUsers(dbUsers,false,true,true);
    }
    if (webPagesFinal != null) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didReceivedWebpagesInUpdates,webPagesFinal);
      for (int b=0, size=webPagesFinal.size(); b < size; b++) {
        long key=webPagesFinal.keyAt(b);
        ArrayList<MessageObject> arrayList=reloadingWebpagesPending.get(key);
        reloadingWebpagesPending.remove(key);
        if (arrayList != null) {
          TLRPC.WebPage webpage=webPagesFinal.valueAt(b);
          ArrayList<TLRPC.Message> arr=new ArrayList<>();
          long dialog_id=0;
          if (webpage instanceof TLRPC.TL_webPage || webpage instanceof TLRPC.TL_webPageEmpty) {
            for (int a=0, size2=arrayList.size(); a < size2; a++) {
              arrayList.get(a).messageOwner.media.webpage=webpage;
              if (a == 0) {
                dialog_id=arrayList.get(a).getDialogId();
                ImageLoader.saveMessageThumbs(arrayList.get(a).messageOwner);
              }
              arr.add(arrayList.get(a).messageOwner);
            }
          }
 else {
            reloadingWebpagesPending.put(webpage.id,arrayList);
          }
          if (!arr.isEmpty()) {
            MessagesStorage.getInstance(currentAccount).putMessages(arr,true,true,false,DownloadController.getInstance(currentAccount).getAutodownloadMask());
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects,dialog_id,arrayList);
          }
        }
      }
    }
    boolean updateDialogs=false;
    if (messagesFinal != null) {
      for (int a=0, size=messagesFinal.size(); a < size; a++) {
        long key=messagesFinal.keyAt(a);
        ArrayList<MessageObject> value=messagesFinal.valueAt(a);
        updateInterfaceWithMessages(key,value);
      }
      updateDialogs=true;
    }
 else     if (forceDialogsUpdate) {
      sortDialogs(null);
      updateDialogs=true;
    }
    if (editingMessagesFinal != null) {
      for (int b=0, size=editingMessagesFinal.size(); b < size; b++) {
        long dialog_id=editingMessagesFinal.keyAt(b);
        ArrayList<MessageObject> arrayList=editingMessagesFinal.valueAt(b);
        MessageObject oldObject=dialogMessage.get(dialog_id);
        if (oldObject != null) {
          for (int a=0, size2=arrayList.size(); a < size2; a++) {
            MessageObject newMessage=arrayList.get(a);
            if (oldObject.getId() == newMessage.getId()) {
              dialogMessage.put(dialog_id,newMessage);
              if (newMessage.messageOwner.to_id != null && newMessage.messageOwner.to_id.channel_id == 0) {
                dialogMessagesByIds.put(newMessage.getId(),newMessage);
              }
              updateDialogs=true;
              break;
            }
 else             if (oldObject.getDialogId() == newMessage.getDialogId() && oldObject.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage && oldObject.replyMessageObject != null && oldObject.replyMessageObject.getId() == newMessage.getId()) {
              oldObject.replyMessageObject=newMessage;
              oldObject.generatePinMessageText(null,null);
              updateDialogs=true;
              break;
            }
          }
        }
        DataQuery.getInstance(currentAccount).loadReplyMessagesForMessages(arrayList,dialog_id);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects,dialog_id,arrayList);
      }
    }
    if (updateDialogs) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
    }
    if (printChangedArg) {
      updateMask|=UPDATE_MASK_USER_PRINT;
    }
    if (contactsIdsFinal != null) {
      updateMask|=UPDATE_MASK_NAME;
      updateMask|=UPDATE_MASK_USER_PHONE;
    }
    if (chatInfoToUpdateFinal != null) {
      for (int a=0, size=chatInfoToUpdateFinal.size(); a < size; a++) {
        TLRPC.ChatParticipants info=chatInfoToUpdateFinal.get(a);
        MessagesStorage.getInstance(currentAccount).updateChatParticipants(info);
      }
    }
    if (channelViewsFinal != null) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didUpdatedMessagesViews,channelViewsFinal);
    }
    if (updateMask != 0) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,updateMask);
    }
  }
);
  final SparseLongArray markAsReadMessagesInboxFinal=markAsReadMessagesInbox;
  final SparseLongArray markAsReadMessagesOutboxFinal=markAsReadMessagesOutbox;
  final ArrayList<Long> markAsReadMessagesFinal=markAsReadMessages;
  final SparseIntArray markAsReadEncryptedFinal=markAsReadEncrypted;
  final SparseArray<ArrayList<Integer>> deletedMessagesFinal=deletedMessages;
  final SparseIntArray clearHistoryMessagesFinal=clearHistoryMessages;
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> {
    int updateMask=0;
    if (markAsReadMessagesInboxFinal != null || markAsReadMessagesOutboxFinal != null) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesRead,markAsReadMessagesInboxFinal,markAsReadMessagesOutboxFinal);
      if (markAsReadMessagesInboxFinal != null) {
        NotificationsController.getInstance(currentAccount).processReadMessages(markAsReadMessagesInboxFinal,0,0,0,false);
        SharedPreferences.Editor editor=notificationsPreferences.edit();
        for (int b=0, size=markAsReadMessagesInboxFinal.size(); b < size; b++) {
          int key=markAsReadMessagesInboxFinal.keyAt(b);
          int messageId=(int)markAsReadMessagesInboxFinal.valueAt(b);
          TLRPC.Dialog dialog=dialogs_dict.get((long)key);
          if (dialog != null && dialog.top_message > 0 && dialog.top_message <= messageId) {
            MessageObject obj=dialogMessage.get(dialog.id);
            if (obj != null && !obj.isOut()) {
              obj.setIsRead();
              updateMask|=UPDATE_MASK_READ_DIALOG_MESSAGE;
            }
          }
          if (key != UserConfig.getInstance(currentAccount).getClientUserId()) {
            editor.remove("diditem" + key);
            editor.remove("diditemo" + key);
          }
        }
        editor.commit();
      }
      if (markAsReadMessagesOutboxFinal != null) {
        for (int b=0, size=markAsReadMessagesOutboxFinal.size(); b < size; b++) {
          int key=markAsReadMessagesOutboxFinal.keyAt(b);
          int messageId=(int)markAsReadMessagesOutboxFinal.valueAt(b);
          TLRPC.Dialog dialog=dialogs_dict.get((long)key);
          if (dialog != null && dialog.top_message > 0 && dialog.top_message <= messageId) {
            MessageObject obj=dialogMessage.get(dialog.id);
            if (obj != null && obj.isOut()) {
              obj.setIsRead();
              updateMask|=UPDATE_MASK_READ_DIALOG_MESSAGE;
            }
          }
        }
      }
    }
    if (markAsReadEncryptedFinal != null) {
      for (int a=0, size=markAsReadEncryptedFinal.size(); a < size; a++) {
        int key=markAsReadEncryptedFinal.keyAt(a);
        int value=markAsReadEncryptedFinal.valueAt(a);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesReadEncrypted,key,value);
        long dialog_id=(long)(key) << 32;
        TLRPC.Dialog dialog=dialogs_dict.get(dialog_id);
        if (dialog != null) {
          MessageObject message=dialogMessage.get(dialog_id);
          if (message != null && message.messageOwner.date <= value) {
            message.setIsRead();
            updateMask|=UPDATE_MASK_READ_DIALOG_MESSAGE;
          }
        }
      }
    }
    if (markAsReadMessagesFinal != null) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesReadContent,markAsReadMessagesFinal);
    }
    if (deletedMessagesFinal != null) {
      for (int a=0, size=deletedMessagesFinal.size(); a < size; a++) {
        int key=deletedMessagesFinal.keyAt(a);
        ArrayList<Integer> arrayList=deletedMessagesFinal.valueAt(a);
        if (arrayList == null) {
          continue;
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesDeleted,arrayList,key);
        if (key == 0) {
          for (int b=0, size2=arrayList.size(); b < size2; b++) {
            Integer id=arrayList.get(b);
            MessageObject obj=dialogMessagesByIds.get(id);
            if (obj != null) {
              obj.deleted=true;
            }
          }
        }
 else {
          MessageObject obj=dialogMessage.get((long)-key);
          if (obj != null) {
            for (int b=0, size2=arrayList.size(); b < size2; b++) {
              if (obj.getId() == arrayList.get(b)) {
                obj.deleted=true;
                break;
              }
            }
          }
        }
      }
      NotificationsController.getInstance(currentAccount).removeDeletedMessagesFromNotifications(deletedMessagesFinal);
    }
    if (clearHistoryMessagesFinal != null) {
      for (int a=0, size=clearHistoryMessagesFinal.size(); a < size; a++) {
        int key=clearHistoryMessagesFinal.keyAt(a);
        int id=clearHistoryMessagesFinal.valueAt(a);
        long did=(long)-key;
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.historyCleared,did,id);
        MessageObject obj=dialogMessage.get(did);
        if (obj != null) {
          if (obj.getId() <= id) {
            obj.deleted=true;
            break;
          }
        }
      }
      NotificationsController.getInstance(currentAccount).removeDeletedHisoryFromNotifications(clearHistoryMessagesFinal);
    }
    if (updateMask != 0) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,updateMask);
    }
  }
));
  if (webPages != null) {
    MessagesStorage.getInstance(currentAccount).putWebPages(webPages);
  }
  if (markAsReadMessagesInbox != null || markAsReadMessagesOutbox != null || markAsReadEncrypted != null || markAsReadMessages != null) {
    if (markAsReadMessagesInbox != null || markAsReadMessages != null) {
      MessagesStorage.getInstance(currentAccount).updateDialogsWithReadMessages(markAsReadMessagesInbox,markAsReadMessagesOutbox,markAsReadMessages,true);
    }
    MessagesStorage.getInstance(currentAccount).markMessagesAsRead(markAsReadMessagesInbox,markAsReadMessagesOutbox,markAsReadEncrypted,true);
  }
  if (markAsReadMessages != null) {
    MessagesStorage.getInstance(currentAccount).markMessagesContentAsRead(markAsReadMessages,ConnectionsManager.getInstance(currentAccount).getCurrentTime());
  }
  if (deletedMessages != null) {
    for (int a=0, size=deletedMessages.size(); a < size; a++) {
      final int key=deletedMessages.keyAt(a);
      final ArrayList<Integer> arrayList=deletedMessages.valueAt(a);
      MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
        ArrayList<Long> dialogIds=MessagesStorage.getInstance(currentAccount).markMessagesAsDeleted(arrayList,false,key);
        MessagesStorage.getInstance(currentAccount).updateDialogsWithDeletedMessages(arrayList,dialogIds,false,key);
      }
);
    }
  }
  if (clearHistoryMessages != null) {
    for (int a=0, size=clearHistoryMessages.size(); a < size; a++) {
      final int key=clearHistoryMessages.keyAt(a);
      final int id=clearHistoryMessages.valueAt(a);
      MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
        ArrayList<Long> dialogIds=MessagesStorage.getInstance(currentAccount).markMessagesAsDeleted(key,id,false);
        MessagesStorage.getInstance(currentAccount).updateDialogsWithDeletedMessages(new ArrayList<>(),dialogIds,false,key);
      }
);
    }
  }
  if (tasks != null) {
    for (int a=0, size=tasks.size(); a < size; a++) {
      TLRPC.TL_updateEncryptedMessagesRead update=tasks.get(a);
      MessagesStorage.getInstance(currentAccount).createTaskForSecretChat(update.chat_id,update.max_date,update.date,1,null);
    }
  }
  return true;
}
