public void markMessagesContentAsRead(final ArrayList<Long> mids,final int date){
  if (isEmpty(mids)) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      String midsStr=TextUtils.join(",",mids);
      database.executeFast(String.format(Locale.US,"UPDATE messages SET read_state = read_state | 2 WHERE mid IN (%s)",midsStr)).stepThis().dispose();
      if (date != 0) {
        SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT mid, ttl FROM messages WHERE mid IN (%s) AND ttl > 0",midsStr));
        ArrayList<Integer> arrayList=null;
        while (cursor.next()) {
          if (arrayList == null) {
            arrayList=new ArrayList<>();
          }
          arrayList.add(cursor.intValue(0));
        }
        if (arrayList != null) {
          emptyMessagesMedia(arrayList);
        }
        cursor.dispose();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
