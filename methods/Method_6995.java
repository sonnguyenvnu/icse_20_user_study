public void deleteContacts(final ArrayList<Integer> uids){
  if (uids == null || uids.isEmpty()) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      String ids=TextUtils.join(",",uids);
      database.executeFast("DELETE FROM contacts WHERE uid IN(" + ids + ")").stepThis().dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
