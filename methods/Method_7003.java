public void putSentFile(final String path,final TLObject file,final int type,String parent){
  if (path == null || file == null || parent == null) {
    return;
  }
  storageQueue.postRunnable(() -> {
    SQLitePreparedStatement state=null;
    try {
      String id=Utilities.MD5(path);
      if (id != null) {
        TLRPC.MessageMedia messageMedia=null;
        if (file instanceof TLRPC.Photo) {
          messageMedia=new TLRPC.TL_messageMediaPhoto();
          messageMedia.photo=(TLRPC.Photo)file;
          messageMedia.flags|=1;
        }
 else         if (file instanceof TLRPC.Document) {
          messageMedia=new TLRPC.TL_messageMediaDocument();
          messageMedia.document=(TLRPC.Document)file;
          messageMedia.flags|=1;
        }
        if (messageMedia == null) {
          return;
        }
        state=database.executeFast("REPLACE INTO sent_files_v2 VALUES(?, ?, ?, ?)");
        state.requery();
        NativeByteBuffer data=new NativeByteBuffer(messageMedia.getObjectSize());
        messageMedia.serializeToStream(data);
        state.bindString(1,id);
        state.bindInteger(2,type);
        state.bindByteBuffer(3,data);
        state.bindString(4,parent);
        state.step();
        data.reuse();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      if (state != null) {
        state.dispose();
      }
    }
  }
);
}
