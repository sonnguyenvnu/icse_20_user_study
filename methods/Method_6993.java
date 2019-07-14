public void loadChatInfo(final int chat_id,final CountDownLatch countDownLatch,final boolean force,final boolean byChannelUsers){
  storageQueue.postRunnable(() -> {
    MessageObject pinnedMessageObject=null;
    TLRPC.ChatFull info=null;
    ArrayList<TLRPC.User> loadedUsers=new ArrayList<>();
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT info, pinned, online FROM chat_settings_v2 WHERE uid = " + chat_id);
      if (cursor.next()) {
        NativeByteBuffer data=cursor.byteBufferValue(0);
        if (data != null) {
          info=TLRPC.ChatFull.TLdeserialize(data,data.readInt32(false),false);
          data.reuse();
          info.pinned_msg_id=cursor.intValue(1);
          info.online_count=cursor.intValue(2);
        }
      }
      cursor.dispose();
      if (info instanceof TLRPC.TL_chatFull) {
        StringBuilder usersToLoad=new StringBuilder();
        for (int a=0; a < info.participants.participants.size(); a++) {
          TLRPC.ChatParticipant c=info.participants.participants.get(a);
          if (usersToLoad.length() != 0) {
            usersToLoad.append(",");
          }
          usersToLoad.append(c.user_id);
        }
        if (usersToLoad.length() != 0) {
          getUsersInternal(usersToLoad.toString(),loadedUsers);
        }
      }
 else       if (info instanceof TLRPC.TL_channelFull) {
        cursor=database.queryFinalized("SELECT us.data, us.status, cu.data, cu.date FROM channel_users_v2 as cu LEFT JOIN users as us ON us.uid = cu.uid WHERE cu.did = " + (-chat_id) + " ORDER BY cu.date DESC");
        info.participants=new TLRPC.TL_chatParticipants();
        while (cursor.next()) {
          try {
            TLRPC.User user=null;
            TLRPC.ChannelParticipant participant=null;
            NativeByteBuffer data=cursor.byteBufferValue(0);
            if (data != null) {
              user=TLRPC.User.TLdeserialize(data,data.readInt32(false),false);
              data.reuse();
            }
            data=cursor.byteBufferValue(2);
            if (data != null) {
              participant=TLRPC.ChannelParticipant.TLdeserialize(data,data.readInt32(false),false);
              data.reuse();
            }
            if (user != null && participant != null) {
              if (user.status != null) {
                user.status.expires=cursor.intValue(1);
              }
              loadedUsers.add(user);
              participant.date=cursor.intValue(3);
              TLRPC.TL_chatChannelParticipant chatChannelParticipant=new TLRPC.TL_chatChannelParticipant();
              chatChannelParticipant.user_id=participant.user_id;
              chatChannelParticipant.date=participant.date;
              chatChannelParticipant.inviter_id=participant.inviter_id;
              chatChannelParticipant.channelParticipant=participant;
              info.participants.participants.add(chatChannelParticipant);
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        cursor.dispose();
        StringBuilder usersToLoad=new StringBuilder();
        for (int a=0; a < info.bot_info.size(); a++) {
          TLRPC.BotInfo botInfo=info.bot_info.get(a);
          if (usersToLoad.length() != 0) {
            usersToLoad.append(",");
          }
          usersToLoad.append(botInfo.user_id);
        }
        if (usersToLoad.length() != 0) {
          getUsersInternal(usersToLoad.toString(),loadedUsers);
        }
      }
      if (countDownLatch != null) {
        countDownLatch.countDown();
      }
      if (info != null && info.pinned_msg_id != 0) {
        pinnedMessageObject=DataQuery.getInstance(currentAccount).loadPinnedMessage(-chat_id,info instanceof TLRPC.TL_channelFull ? chat_id : 0,info.pinned_msg_id,false);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      MessagesController.getInstance(currentAccount).processChatInfo(chat_id,info,loadedUsers,true,force,byChannelUsers,pinnedMessageObject);
      if (countDownLatch != null) {
        countDownLatch.countDown();
      }
    }
  }
);
}
