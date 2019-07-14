private void saveSharingLocation(final SharingLocationInfo info,final int remove){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      if (remove == 2) {
        MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM sharing_locations WHERE 1").stepThis().dispose();
      }
 else       if (remove == 1) {
        if (info == null) {
          return;
        }
        MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM sharing_locations WHERE uid = " + info.did).stepThis().dispose();
      }
 else {
        if (info == null) {
          return;
        }
        SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO sharing_locations VALUES(?, ?, ?, ?, ?)");
        state.requery();
        NativeByteBuffer data=new NativeByteBuffer(info.messageObject.messageOwner.getObjectSize());
        info.messageObject.messageOwner.serializeToStream(data);
        state.bindLong(1,info.did);
        state.bindInteger(2,info.mid);
        state.bindInteger(3,info.stopTime);
        state.bindInteger(4,info.period);
        state.bindByteBuffer(5,data);
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
