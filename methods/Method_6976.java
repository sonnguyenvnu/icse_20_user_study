public void getNewTask(final ArrayList<Integer> oldTask,final int channelId){
  storageQueue.postRunnable(() -> {
    try {
      if (oldTask != null) {
        String ids=TextUtils.join(",",oldTask);
        database.executeFast(String.format(Locale.US,"DELETE FROM enc_tasks_v2 WHERE mid IN(%s)",ids)).stepThis().dispose();
      }
      int date=0;
      int channelId1=-1;
      ArrayList<Integer> arr=null;
      SQLiteCursor cursor=database.queryFinalized("SELECT mid, date FROM enc_tasks_v2 WHERE date = (SELECT min(date) FROM enc_tasks_v2)");
      while (cursor.next()) {
        long mid=cursor.longValue(0);
        if (channelId1 == -1) {
          channelId1=(int)(mid >> 32);
          if (channelId1 < 0) {
            channelId1=0;
          }
        }
        date=cursor.intValue(1);
        if (arr == null) {
          arr=new ArrayList<>();
        }
        arr.add((int)mid);
      }
      cursor.dispose();
      MessagesController.getInstance(currentAccount).processLoadedDeleteTask(date,arr,channelId1);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
