public void unpinAllDialogsExceptNew(final ArrayList<Long> dids,int folderId){
  storageQueue.postRunnable(() -> {
    try {
      ArrayList<Long> unpinnedDialogs=new ArrayList<>();
      SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT did, folder_id FROM dialogs WHERE pinned != 0 AND did NOT IN (%s)",TextUtils.join(",",dids)));
      while (cursor.next()) {
        long did=cursor.longValue(0);
        int fid=cursor.intValue(1);
        if (fid == folderId && (int)did != 0 && !DialogObject.isFolderDialogId(did)) {
          unpinnedDialogs.add(cursor.longValue(0));
        }
      }
      cursor.dispose();
      if (!unpinnedDialogs.isEmpty()) {
        SQLitePreparedStatement state=database.executeFast("UPDATE dialogs SET pinned = ? WHERE did = ?");
        for (int a=0; a < unpinnedDialogs.size(); a++) {
          long did=unpinnedDialogs.get(a);
          state.requery();
          state.bindInteger(1,0);
          state.bindLong(2,did);
          state.step();
        }
        state.dispose();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
