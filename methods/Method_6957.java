private void fixNotificationSettings(){
  storageQueue.postRunnable(() -> {
    try {
      LongSparseArray<Long> ids=new LongSparseArray<>();
      SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
      Map<String,?> values=preferences.getAll();
      for (      Map.Entry<String,?> entry : values.entrySet()) {
        String key=entry.getKey();
        if (key.startsWith("notify2_")) {
          Integer value=(Integer)entry.getValue();
          if (value == 2 || value == 3) {
            key=key.replace("notify2_","");
            long flags;
            if (value == 2) {
              flags=1;
            }
 else {
              Integer time=(Integer)values.get("notifyuntil_" + key);
              if (time != null) {
                flags=((long)time << 32) | 1;
              }
 else {
                flags=1;
              }
            }
            try {
              ids.put(Long.parseLong(key),flags);
            }
 catch (            Exception e) {
              e.printStackTrace();
            }
          }
        }
      }
      try {
        database.beginTransaction();
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO dialog_settings VALUES(?, ?)");
        for (int a=0; a < ids.size(); a++) {
          state.requery();
          state.bindLong(1,ids.keyAt(a));
          state.bindLong(2,ids.valueAt(a));
          state.step();
        }
        state.dispose();
        database.commitTransaction();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
);
}
