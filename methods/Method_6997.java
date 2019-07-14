public void getCachedPhoneBook(final boolean byError){
  storageQueue.postRunnable(() -> {
    SQLiteCursor cursor=null;
    try {
      cursor=database.queryFinalized("SELECT name FROM sqlite_master WHERE type='table' AND name='user_contacts_v6'");
      boolean migrate=cursor.next();
      cursor.dispose();
      cursor=null;
      if (migrate) {
        int count=16;
        cursor=database.queryFinalized("SELECT COUNT(uid) FROM user_contacts_v6 WHERE 1");
        if (cursor.next()) {
          count=Math.min(5000,cursor.intValue(0));
        }
        cursor.dispose();
        SparseArray<ContactsController.Contact> contactHashMap=new SparseArray<>(count);
        cursor=database.queryFinalized("SELECT us.uid, us.fname, us.sname, up.phone, up.sphone, up.deleted, us.imported FROM user_contacts_v6 as us LEFT JOIN user_phones_v6 as up ON us.uid = up.uid WHERE 1");
        while (cursor.next()) {
          int uid=cursor.intValue(0);
          ContactsController.Contact contact=contactHashMap.get(uid);
          if (contact == null) {
            contact=new ContactsController.Contact();
            contact.first_name=cursor.stringValue(1);
            contact.last_name=cursor.stringValue(2);
            contact.imported=cursor.intValue(6);
            if (contact.first_name == null) {
              contact.first_name="";
            }
            if (contact.last_name == null) {
              contact.last_name="";
            }
            contact.contact_id=uid;
            contactHashMap.put(uid,contact);
          }
          String phone=cursor.stringValue(3);
          if (phone == null) {
            continue;
          }
          contact.phones.add(phone);
          String sphone=cursor.stringValue(4);
          if (sphone == null) {
            continue;
          }
          if (sphone.length() == 8 && phone.length() != 8) {
            sphone=PhoneFormat.stripExceptNumbers(phone);
          }
          contact.shortPhones.add(sphone);
          contact.phoneDeleted.add(cursor.intValue(5));
          contact.phoneTypes.add("");
          if (contactHashMap.size() == 5000) {
            break;
          }
        }
        cursor.dispose();
        cursor=null;
        ContactsController.getInstance(currentAccount).migratePhoneBookToV7(contactHashMap);
        return;
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
 finally {
      if (cursor != null) {
        cursor.dispose();
      }
    }
    int count=16;
    int currentContactsCount=0;
    int start=0;
    try {
      cursor=database.queryFinalized("SELECT COUNT(key) FROM user_contacts_v7 WHERE 1");
      if (cursor.next()) {
        currentContactsCount=cursor.intValue(0);
        count=Math.min(5000,currentContactsCount);
        if (currentContactsCount > 5000) {
          start=currentContactsCount - 5000;
        }
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d(currentAccount + " current cached contacts count = " + currentContactsCount);
        }
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
 finally {
      if (cursor != null) {
        cursor.dispose();
      }
    }
    HashMap<String,ContactsController.Contact> contactHashMap=new HashMap<>(count);
    try {
      if (start != 0) {
        cursor=database.queryFinalized("SELECT us.key, us.uid, us.fname, us.sname, up.phone, up.sphone, up.deleted, us.imported FROM user_contacts_v7 as us LEFT JOIN user_phones_v7 as up ON us.key = up.key WHERE 1 LIMIT " + 0 + "," + currentContactsCount);
      }
 else {
        cursor=database.queryFinalized("SELECT us.key, us.uid, us.fname, us.sname, up.phone, up.sphone, up.deleted, us.imported FROM user_contacts_v7 as us LEFT JOIN user_phones_v7 as up ON us.key = up.key WHERE 1");
      }
      while (cursor.next()) {
        String key=cursor.stringValue(0);
        ContactsController.Contact contact=contactHashMap.get(key);
        if (contact == null) {
          contact=new ContactsController.Contact();
          contact.contact_id=cursor.intValue(1);
          contact.first_name=cursor.stringValue(2);
          contact.last_name=cursor.stringValue(3);
          contact.imported=cursor.intValue(7);
          if (contact.first_name == null) {
            contact.first_name="";
          }
          if (contact.last_name == null) {
            contact.last_name="";
          }
          contactHashMap.put(key,contact);
        }
        String phone=cursor.stringValue(4);
        if (phone == null) {
          continue;
        }
        contact.phones.add(phone);
        String sphone=cursor.stringValue(5);
        if (sphone == null) {
          continue;
        }
        if (sphone.length() == 8 && phone.length() != 8) {
          sphone=PhoneFormat.stripExceptNumbers(phone);
        }
        contact.shortPhones.add(sphone);
        contact.phoneDeleted.add(cursor.intValue(6));
        contact.phoneTypes.add("");
        if (contactHashMap.size() == 5000) {
          break;
        }
      }
      cursor.dispose();
      cursor=null;
    }
 catch (    Exception e) {
      contactHashMap.clear();
      FileLog.e(e);
    }
 finally {
      if (cursor != null) {
        cursor.dispose();
      }
    }
    ContactsController.getInstance(currentAccount).performSyncPhoneBook(contactHashMap,true,true,false,false,!byError,false);
  }
);
}
