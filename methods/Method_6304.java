protected void migratePhoneBookToV7(final SparseArray<Contact> contactHashMap){
  Utilities.globalQueue.postRunnable(() -> {
    if (migratingContacts) {
      return;
    }
    migratingContacts=true;
    HashMap<String,Contact> migratedMap=new HashMap<>();
    HashMap<String,Contact> contactsMap=readContactsFromPhoneBook();
    final HashMap<String,String> contactsBookShort=new HashMap<>();
    for (    HashMap.Entry<String,Contact> entry : contactsMap.entrySet()) {
      Contact value=entry.getValue();
      for (int a=0; a < value.shortPhones.size(); a++) {
        contactsBookShort.put(value.shortPhones.get(a),value.key);
      }
    }
    for (int b=0; b < contactHashMap.size(); b++) {
      Contact value=contactHashMap.valueAt(b);
      for (int a=0; a < value.shortPhones.size(); a++) {
        String sphone=value.shortPhones.get(a);
        String key=contactsBookShort.get(sphone);
        if (key != null) {
          value.key=key;
          migratedMap.put(key,value);
          break;
        }
      }
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("migrated contacts " + migratedMap.size() + " of " + contactHashMap.size());
    }
    MessagesStorage.getInstance(currentAccount).putCachedPhoneBook(migratedMap,true,false);
  }
);
}
