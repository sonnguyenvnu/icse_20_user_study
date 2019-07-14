public void putContacts(ArrayList<TLRPC.TL_contact> contacts,final boolean deleteAll){
  if (contacts.isEmpty() && !deleteAll) {
    return;
  }
  final ArrayList<TLRPC.TL_contact> contactsCopy=new ArrayList<>(contacts);
  storageQueue.postRunnable(() -> {
    try {
      if (deleteAll) {
        database.executeFast("DELETE FROM contacts WHERE 1").stepThis().dispose();
      }
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO contacts VALUES(?, ?)");
      for (int a=0; a < contactsCopy.size(); a++) {
        TLRPC.TL_contact contact=contactsCopy.get(a);
        state.requery();
        state.bindInteger(1,contact.user_id);
        state.bindInteger(2,contact.mutual ? 1 : 0);
        state.step();
      }
      state.dispose();
      database.commitTransaction();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
