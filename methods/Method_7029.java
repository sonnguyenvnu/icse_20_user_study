public void markMessagesAsDeletedByRandoms(final ArrayList<Long> messages){
  if (messages.isEmpty()) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      String ids=TextUtils.join(",",messages);
      SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT mid FROM randoms WHERE random_id IN(%s)",ids));
      final ArrayList<Integer> mids=new ArrayList<>();
      while (cursor.next()) {
        mids.add(cursor.intValue(0));
      }
      cursor.dispose();
      if (!mids.isEmpty()) {
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messagesDeleted,mids,0));
        updateDialogsWithReadMessagesInternal(mids,null,null,null);
        markMessagesAsDeletedInternal(mids,0);
        updateDialogsWithDeletedMessagesInternal(mids,null,0);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
