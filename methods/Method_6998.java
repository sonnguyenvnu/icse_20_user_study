public void getContacts(){
  storageQueue.postRunnable(() -> {
    ArrayList<TLRPC.TL_contact> contacts=new ArrayList<>();
    ArrayList<TLRPC.User> users=new ArrayList<>();
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT * FROM contacts WHERE 1");
      StringBuilder uids=new StringBuilder();
      while (cursor.next()) {
        int user_id=cursor.intValue(0);
        TLRPC.TL_contact contact=new TLRPC.TL_contact();
        contact.user_id=user_id;
        contact.mutual=cursor.intValue(1) == 1;
        if (uids.length() != 0) {
          uids.append(",");
        }
        contacts.add(contact);
        uids.append(contact.user_id);
      }
      cursor.dispose();
      if (uids.length() != 0) {
        getUsersInternal(uids.toString(),users);
      }
    }
 catch (    Exception e) {
      contacts.clear();
      users.clear();
      FileLog.e(e);
    }
    ContactsController.getInstance(currentAccount).processLoadedContacts(contacts,users,1);
  }
);
}
