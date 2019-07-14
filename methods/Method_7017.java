public void putWebPages(final LongSparseArray<TLRPC.WebPage> webPages){
  if (isEmpty(webPages)) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      final ArrayList<TLRPC.Message> messages=new ArrayList<>();
      for (int a=0; a < webPages.size(); a++) {
        SQLiteCursor cursor=database.queryFinalized("SELECT mid FROM webpage_pending WHERE id = " + webPages.keyAt(a));
        ArrayList<Long> mids=new ArrayList<>();
        while (cursor.next()) {
          mids.add(cursor.longValue(0));
        }
        cursor.dispose();
        if (mids.isEmpty()) {
          continue;
        }
        cursor=database.queryFinalized(String.format(Locale.US,"SELECT mid, data FROM messages WHERE mid IN (%s)",TextUtils.join(",",mids)));
        while (cursor.next()) {
          int mid=cursor.intValue(0);
          NativeByteBuffer data=cursor.byteBufferValue(1);
          if (data != null) {
            TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
            message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
            data.reuse();
            if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
              message.id=mid;
              message.media.webpage=webPages.valueAt(a);
              messages.add(message);
            }
          }
        }
        cursor.dispose();
      }
      if (messages.isEmpty()) {
        return;
      }
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("UPDATE messages SET data = ? WHERE mid = ?");
      SQLitePreparedStatement state2=database.executeFast("UPDATE media_v2 SET data = ? WHERE mid = ?");
      for (int a=0; a < messages.size(); a++) {
        TLRPC.Message message=messages.get(a);
        NativeByteBuffer data=new NativeByteBuffer(message.getObjectSize());
        message.serializeToStream(data);
        long messageId=message.id;
        if (message.to_id.channel_id != 0) {
          messageId|=((long)message.to_id.channel_id) << 32;
        }
        state.requery();
        state.bindByteBuffer(1,data);
        state.bindLong(2,messageId);
        state.step();
        state2.requery();
        state2.bindByteBuffer(1,data);
        state2.bindLong(2,messageId);
        state2.step();
        data.reuse();
      }
      state.dispose();
      state2.dispose();
      database.commitTransaction();
      AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didReceivedWebpages,messages));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
