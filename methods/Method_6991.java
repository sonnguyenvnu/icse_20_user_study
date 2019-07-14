public void updateChatInfo(final int chat_id,final int user_id,final int what,final int invited_id,final int version){
  storageQueue.postRunnable(() -> {
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT info, pinned, online FROM chat_settings_v2 WHERE uid = " + chat_id);
      TLRPC.ChatFull info=null;
      ArrayList<TLRPC.User> loadedUsers=new ArrayList<>();
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
        if (what == 1) {
          for (int a=0; a < info.participants.participants.size(); a++) {
            TLRPC.ChatParticipant participant=info.participants.participants.get(a);
            if (participant.user_id == user_id) {
              info.participants.participants.remove(a);
              break;
            }
          }
        }
 else         if (what == 0) {
          for (          TLRPC.ChatParticipant part : info.participants.participants) {
            if (part.user_id == user_id) {
              return;
            }
          }
          TLRPC.TL_chatParticipant participant=new TLRPC.TL_chatParticipant();
          participant.user_id=user_id;
          participant.inviter_id=invited_id;
          participant.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
          info.participants.participants.add(participant);
        }
 else         if (what == 2) {
          for (int a=0; a < info.participants.participants.size(); a++) {
            TLRPC.ChatParticipant participant=info.participants.participants.get(a);
            if (participant.user_id == user_id) {
              TLRPC.ChatParticipant newParticipant;
              if (invited_id == 1) {
                newParticipant=new TLRPC.TL_chatParticipantAdmin();
                newParticipant.user_id=participant.user_id;
                newParticipant.date=participant.date;
                newParticipant.inviter_id=participant.inviter_id;
              }
 else {
                newParticipant=new TLRPC.TL_chatParticipant();
                newParticipant.user_id=participant.user_id;
                newParticipant.date=participant.date;
                newParticipant.inviter_id=participant.inviter_id;
              }
              info.participants.participants.set(a,newParticipant);
              break;
            }
          }
        }
        info.participants.version=version;
        final TLRPC.ChatFull finalInfo=info;
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,finalInfo,0,false,null));
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO chat_settings_v2 VALUES(?, ?, ?, ?)");
        NativeByteBuffer data=new NativeByteBuffer(info.getObjectSize());
        info.serializeToStream(data);
        state.bindInteger(1,chat_id);
        state.bindByteBuffer(2,data);
        state.bindInteger(3,info.pinned_msg_id);
        state.bindInteger(4,info.online_count);
        state.step();
        state.dispose();
        data.reuse();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
