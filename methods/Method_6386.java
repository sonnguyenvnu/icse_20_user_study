private MessageObject loadPinnedMessageInternal(final long dialogId,final int channelId,final int mid,boolean returnValue){
  try {
    long messageId;
    if (channelId != 0) {
      messageId=((long)mid) | ((long)channelId) << 32;
    }
 else {
      messageId=mid;
    }
    TLRPC.Message result=null;
    final ArrayList<TLRPC.User> users=new ArrayList<>();
    final ArrayList<TLRPC.Chat> chats=new ArrayList<>();
    ArrayList<Integer> usersToLoad=new ArrayList<>();
    ArrayList<Integer> chatsToLoad=new ArrayList<>();
    SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT data, mid, date FROM messages WHERE mid = %d",messageId));
    if (cursor.next()) {
      NativeByteBuffer data=cursor.byteBufferValue(0);
      if (data != null) {
        result=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
        result.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
        data.reuse();
        if (result.action instanceof TLRPC.TL_messageActionHistoryClear) {
          result=null;
        }
 else {
          result.id=cursor.intValue(1);
          result.date=cursor.intValue(2);
          result.dialog_id=dialogId;
          MessagesStorage.addUsersAndChatsFromMessage(result,usersToLoad,chatsToLoad);
        }
      }
    }
    cursor.dispose();
    if (result == null) {
      cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT data FROM chat_pinned WHERE uid = %d",dialogId));
      if (cursor.next()) {
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          result=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
          result.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
          data.reuse();
          if (result.id != mid || result.action instanceof TLRPC.TL_messageActionHistoryClear) {
            result=null;
          }
 else {
            result.dialog_id=dialogId;
            MessagesStorage.addUsersAndChatsFromMessage(result,usersToLoad,chatsToLoad);
          }
        }
      }
      cursor.dispose();
    }
    if (result == null) {
      if (channelId != 0) {
        final TLRPC.TL_channels_getMessages req=new TLRPC.TL_channels_getMessages();
        req.channel=MessagesController.getInstance(currentAccount).getInputChannel(channelId);
        req.id.add(mid);
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
          boolean ok=false;
          if (error == null) {
            TLRPC.messages_Messages messagesRes=(TLRPC.messages_Messages)response;
            removeEmptyMessages(messagesRes.messages);
            if (!messagesRes.messages.isEmpty()) {
              ImageLoader.saveMessagesThumbs(messagesRes.messages);
              broadcastPinnedMessage(messagesRes.messages.get(0),messagesRes.users,messagesRes.chats,false,false);
              MessagesStorage.getInstance(currentAccount).putUsersAndChats(messagesRes.users,messagesRes.chats,true,true);
              savePinnedMessage(messagesRes.messages.get(0));
              ok=true;
            }
          }
          if (!ok) {
            MessagesStorage.getInstance(currentAccount).updateChatPinnedMessage(channelId,0);
          }
        }
);
      }
 else {
        final TLRPC.TL_messages_getMessages req=new TLRPC.TL_messages_getMessages();
        req.id.add(mid);
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
          boolean ok=false;
          if (error == null) {
            TLRPC.messages_Messages messagesRes=(TLRPC.messages_Messages)response;
            removeEmptyMessages(messagesRes.messages);
            if (!messagesRes.messages.isEmpty()) {
              ImageLoader.saveMessagesThumbs(messagesRes.messages);
              broadcastPinnedMessage(messagesRes.messages.get(0),messagesRes.users,messagesRes.chats,false,false);
              MessagesStorage.getInstance(currentAccount).putUsersAndChats(messagesRes.users,messagesRes.chats,true,true);
              savePinnedMessage(messagesRes.messages.get(0));
              ok=true;
            }
          }
          if (!ok) {
            MessagesStorage.getInstance(currentAccount).updateChatPinnedMessage(channelId,0);
          }
        }
);
      }
    }
 else {
      if (returnValue) {
        return broadcastPinnedMessage(result,users,chats,true,returnValue);
      }
 else {
        if (!usersToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getUsersInternal(TextUtils.join(",",usersToLoad),users);
        }
        if (!chatsToLoad.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).getChatsInternal(TextUtils.join(",",chatsToLoad),chats);
        }
        broadcastPinnedMessage(result,users,chats,true,false);
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}
