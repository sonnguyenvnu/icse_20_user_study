public void updateChannelUsers(final int channel_id,final ArrayList<TLRPC.ChannelParticipant> participants){
  storageQueue.postRunnable(() -> {
    try {
      long did=-channel_id;
      database.executeFast("DELETE FROM channel_users_v2 WHERE did = " + did).stepThis().dispose();
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO channel_users_v2 VALUES(?, ?, ?, ?)");
      NativeByteBuffer data;
      int date=(int)(System.currentTimeMillis() / 1000);
      for (int a=0; a < participants.size(); a++) {
        TLRPC.ChannelParticipant participant=participants.get(a);
        state.requery();
        state.bindLong(1,did);
        state.bindInteger(2,participant.user_id);
        state.bindInteger(3,date);
        data=new NativeByteBuffer(participant.getObjectSize());
        participant.serializeToStream(data);
        state.bindByteBuffer(4,data);
        data.reuse();
        state.step();
        date--;
      }
      state.dispose();
      database.commitTransaction();
      loadChatInfo(channel_id,null,false,true);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
