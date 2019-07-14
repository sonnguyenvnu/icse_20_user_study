public void saveSecretParams(final int lsv,final int sg,final byte[] pbytes){
  storageQueue.postRunnable(() -> {
    try {
      SQLitePreparedStatement state=database.executeFast("UPDATE params SET lsv = ?, sg = ?, pbytes = ? WHERE id = 1");
      state.bindInteger(1,lsv);
      state.bindInteger(2,sg);
      NativeByteBuffer data=new NativeByteBuffer(pbytes != null ? pbytes.length : 1);
      if (pbytes != null) {
        data.writeBytes(pbytes);
      }
      state.bindByteBuffer(3,data);
      state.step();
      state.dispose();
      data.reuse();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
