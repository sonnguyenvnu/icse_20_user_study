public void createTaskForMid(final int messageId,final int channelId,final int time,final int readTime,final int ttl,final boolean inner){
  storageQueue.postRunnable(() -> {
    try {
      int minDate=(time > readTime ? time : readTime) + ttl;
      SparseArray<ArrayList<Long>> messages=new SparseArray<>();
      final ArrayList<Long> midsArray=new ArrayList<>();
      long mid=messageId;
      if (channelId != 0) {
        mid|=((long)channelId) << 32;
      }
      midsArray.add(mid);
      messages.put(minDate,midsArray);
      AndroidUtilities.runOnUIThread(() -> {
        if (!inner) {
          markMessagesContentAsRead(midsArray,0);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesReadContent,midsArray);
      }
);
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
      database.executeFast(String.format(Locale.US,"UPDATE messages SET ttl = 0 WHERE mid = %d",mid)).stepThis().dispose();
      MessagesController.getInstance(currentAccount).didAddedNewTask(minDate,messages);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
