public long createPendingTask(final NativeByteBuffer data){
  if (data == null) {
    return 0;
  }
  final long id=lastTaskId.getAndAdd(1);
  storageQueue.postRunnable(() -> {
    try {
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO pending_tasks VALUES(?, ?)");
      state.bindLong(1,id);
      state.bindByteBuffer(2,data);
      state.step();
      state.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
 finally {
      data.reuse();
    }
  }
);
  return id;
}
