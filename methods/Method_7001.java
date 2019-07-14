public void clearSentMedia(){
  storageQueue.postRunnable(() -> {
    try {
      database.executeFast("DELETE FROM sent_files_v2 WHERE 1").stepThis().dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
