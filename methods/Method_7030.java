protected void deletePushMessages(long dialogId,final ArrayList<Integer> messages){
  try {
    database.executeFast(String.format(Locale.US,"DELETE FROM unread_push_messages WHERE uid = %d AND mid IN(%s)",dialogId,TextUtils.join(",",messages))).stepThis().dispose();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
