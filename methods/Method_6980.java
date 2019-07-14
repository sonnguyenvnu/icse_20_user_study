public void createTaskForSecretChat(final int chatId,final int time,final int readTime,final int isOut,final ArrayList<Long> random_ids){
  storageQueue.postRunnable(() -> {
    try {
      int minDate=Integer.MAX_VALUE;
      SparseArray<ArrayList<Long>> messages=new SparseArray<>();
      final ArrayList<Long> midsArray=new ArrayList<>();
      StringBuilder mids=new StringBuilder();
      SQLiteCursor cursor;
      if (random_ids == null) {
        cursor=database.queryFinalized(String.format(Locale.US,"SELECT mid, ttl FROM messages WHERE uid = %d AND out = %d AND read_state != 0 AND ttl > 0 AND date <= %d AND send_state = 0 AND media != 1",((long)chatId) << 32,isOut,time));
      }
 else {
        String ids=TextUtils.join(",",random_ids);
        cursor=database.queryFinalized(String.format(Locale.US,"SELECT m.mid, m.ttl FROM messages as m INNER JOIN randoms as r ON m.mid = r.mid WHERE r.random_id IN (%s)",ids));
      }
      while (cursor.next()) {
        int ttl=cursor.intValue(1);
        long mid=cursor.intValue(0);
        if (random_ids != null) {
          midsArray.add(mid);
        }
        if (ttl <= 0) {
          continue;
        }
        int date=(time > readTime ? time : readTime) + ttl;
        minDate=Math.min(minDate,date);
        ArrayList<Long> arr=messages.get(date);
        if (arr == null) {
          arr=new ArrayList<>();
          messages.put(date,arr);
        }
        if (mids.length() != 0) {
          mids.append(",");
        }
        mids.append(mid);
        arr.add(mid);
      }
      cursor.dispose();
      if (random_ids != null) {
        AndroidUtilities.runOnUIThread(() -> {
          markMessagesContentAsRead(midsArray,0);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesReadContent,midsArray);
        }
);
      }
      if (messages.size() != 0) {
        database.beginTransaction();
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO enc_tasks_v2 VALUES(?, ?)");
        for (int a=0; a < messages.size(); a++) {
          int key=messages.keyAt(a);
          ArrayList<Long> arr=messages.get(key);
          for (int b=0; b < arr.size(); b++) {
            state.requery();
            state.bindLong(1,arr.get(b));
            state.bindInteger(2,key);
            state.step();
          }
        }
        state.dispose();
        database.commitTransaction();
        database.executeFast(String.format(Locale.US,"UPDATE messages SET ttl = 0 WHERE mid IN(%s)",mids.toString())).stepThis().dispose();
        MessagesController.getInstance(currentAccount).didAddedNewTask(minDate,messages);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
