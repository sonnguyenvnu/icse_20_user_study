public void putDialogPhotos(final int did,final TLRPC.photos_Photos photos){
  if (photos == null || photos.photos.isEmpty()) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      database.executeFast("DELETE FROM user_photos WHERE uid = " + did).stepThis().dispose();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO user_photos VALUES(?, ?, ?)");
      for (int a=0, N=photos.photos.size(); a < N; a++) {
        TLRPC.Photo photo=photos.photos.get(a);
        if (photo instanceof TLRPC.TL_photoEmpty) {
          continue;
        }
        state.requery();
        NativeByteBuffer data=new NativeByteBuffer(photo.getObjectSize());
        photo.serializeToStream(data);
        state.bindInteger(1,did);
        state.bindLong(2,photo.id);
        state.bindByteBuffer(3,data);
        state.step();
        data.reuse();
      }
      state.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
