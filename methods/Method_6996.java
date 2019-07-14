public void putCachedPhoneBook(final HashMap<String,ContactsController.Contact> contactHashMap,final boolean migrate,final boolean delete){
  if (contactHashMap == null || contactHashMap.isEmpty() && !migrate && !delete) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d(currentAccount + " save contacts to db " + contactHashMap.size());
      }
      database.executeFast("DELETE FROM user_contacts_v7 WHERE 1").stepThis().dispose();
      database.executeFast("DELETE FROM user_phones_v7 WHERE 1").stepThis().dispose();
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO user_contacts_v7 VALUES(?, ?, ?, ?, ?)");
      SQLitePreparedStatement state2=database.executeFast("REPLACE INTO user_phones_v7 VALUES(?, ?, ?, ?)");
      for (      HashMap.Entry<String,ContactsController.Contact> entry : contactHashMap.entrySet()) {
        ContactsController.Contact contact=entry.getValue();
        if (contact.phones.isEmpty() || contact.shortPhones.isEmpty()) {
          continue;
        }
        state.requery();
        state.bindString(1,contact.key);
        state.bindInteger(2,contact.contact_id);
        state.bindString(3,contact.first_name);
        state.bindString(4,contact.last_name);
        state.bindInteger(5,contact.imported);
        state.step();
        for (int a=0; a < contact.phones.size(); a++) {
          state2.requery();
          state2.bindString(1,contact.key);
          state2.bindString(2,contact.phones.get(a));
          state2.bindString(3,contact.shortPhones.get(a));
          state2.bindInteger(4,contact.phoneDeleted.get(a));
          state2.step();
        }
      }
      state.dispose();
      state2.dispose();
      database.commitTransaction();
      if (migrate) {
        database.executeFast("DROP TABLE IF EXISTS user_contacts_v6;").stepThis().dispose();
        database.executeFast("DROP TABLE IF EXISTS user_phones_v6;").stepThis().dispose();
        getCachedPhoneBook(false);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
