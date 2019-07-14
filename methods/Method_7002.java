public Object[] getSentFile(final String path,final int type){
  if (path == null || path.toLowerCase().endsWith("attheme")) {
    return null;
  }
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  final Object[] result=new Object[2];
  storageQueue.postRunnable(() -> {
    try {
      String id=Utilities.MD5(path);
      if (id != null) {
        SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT data, parent FROM sent_files_v2 WHERE uid = '%s' AND type = %d",id,type));
        if (cursor.next()) {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            TLObject file=TLRPC.MessageMedia.TLdeserialize(data,data.readInt32(false),false);
            data.reuse();
            if (file instanceof TLRPC.TL_messageMediaDocument) {
              result[0]=((TLRPC.TL_messageMediaDocument)file).document;
            }
 else             if (file instanceof TLRPC.TL_messageMediaPhoto) {
              result[0]=((TLRPC.TL_messageMediaPhoto)file).photo;
            }
            if (result[0] != null) {
              result[1]=cursor.stringValue(1);
            }
          }
        }
        cursor.dispose();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      countDownLatch.countDown();
    }
  }
);
  try {
    countDownLatch.await();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return result[0] != null ? result : null;
}
