private void putSetToCache(TLRPC.TL_messages_stickerSet set){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      SQLiteDatabase database=MessagesStorage.getInstance(currentAccount).getDatabase();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO web_recent_v3 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      state.requery();
      state.bindString(1,"s_" + set.set.id);
      state.bindInteger(2,6);
      state.bindString(3,"");
      state.bindString(4,"");
      state.bindString(5,"");
      state.bindInteger(6,0);
      state.bindInteger(7,0);
      state.bindInteger(8,0);
      state.bindInteger(9,0);
      NativeByteBuffer data=new NativeByteBuffer(set.getObjectSize());
      set.serializeToStream(data);
      state.bindByteBuffer(10,data);
      state.step();
      data.reuse();
      state.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
