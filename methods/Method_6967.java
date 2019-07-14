public void addRecentLocalFile(final String imageUrl,final String localUrl,final TLRPC.Document document){
  if (imageUrl == null || imageUrl.length() == 0 || ((localUrl == null || localUrl.length() == 0) && document == null)) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      if (document != null) {
        SQLitePreparedStatement state=database.executeFast("UPDATE web_recent_v3 SET document = ? WHERE image_url = ?");
        state.requery();
        NativeByteBuffer data=new NativeByteBuffer(document.getObjectSize());
        document.serializeToStream(data);
        state.bindByteBuffer(1,data);
        state.bindString(2,imageUrl);
        state.step();
        state.dispose();
        data.reuse();
      }
 else {
        SQLitePreparedStatement state=database.executeFast("UPDATE web_recent_v3 SET local_url = ? WHERE image_url = ?");
        state.requery();
        state.bindString(1,localUrl);
        state.bindString(2,imageUrl);
        state.step();
        state.dispose();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
