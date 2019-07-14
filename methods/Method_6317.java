private void applyContactsUpdates(ArrayList<Integer> ids,ConcurrentHashMap<Integer,TLRPC.User> userDict,ArrayList<TLRPC.TL_contact> newC,ArrayList<Integer> contactsTD){
  if (newC == null || contactsTD == null) {
    newC=new ArrayList<>();
    contactsTD=new ArrayList<>();
    for (int a=0; a < ids.size(); a++) {
      Integer uid=ids.get(a);
      if (uid > 0) {
        TLRPC.TL_contact contact=new TLRPC.TL_contact();
        contact.user_id=uid;
        newC.add(contact);
      }
 else       if (uid < 0) {
        contactsTD.add(-uid);
      }
    }
  }
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("process update - contacts add = " + newC.size() + " delete = " + contactsTD.size());
  }
  StringBuilder toAdd=new StringBuilder();
  StringBuilder toDelete=new StringBuilder();
  boolean reloadContacts=false;
  for (int a=0; a < newC.size(); a++) {
    TLRPC.TL_contact newContact=newC.get(a);
    TLRPC.User user=null;
    if (userDict != null) {
      user=userDict.get(newContact.user_id);
    }
    if (user == null) {
      user=MessagesController.getInstance(currentAccount).getUser(newContact.user_id);
    }
 else {
      MessagesController.getInstance(currentAccount).putUser(user,true);
    }
    if (user == null || TextUtils.isEmpty(user.phone)) {
      reloadContacts=true;
      continue;
    }
    Contact contact=contactsBookSPhones.get(user.phone);
    if (contact != null) {
      int index=contact.shortPhones.indexOf(user.phone);
      if (index != -1) {
        contact.phoneDeleted.set(index,0);
      }
    }
    if (toAdd.length() != 0) {
      toAdd.append(",");
    }
    toAdd.append(user.phone);
  }
  for (int a=0; a < contactsTD.size(); a++) {
    final Integer uid=contactsTD.get(a);
    Utilities.phoneBookQueue.postRunnable(() -> deleteContactFromPhoneBook(uid));
    TLRPC.User user=null;
    if (userDict != null) {
      user=userDict.get(uid);
    }
    if (user == null) {
      user=MessagesController.getInstance(currentAccount).getUser(uid);
    }
 else {
      MessagesController.getInstance(currentAccount).putUser(user,true);
    }
    if (user == null) {
      reloadContacts=true;
      continue;
    }
    if (!TextUtils.isEmpty(user.phone)) {
      Contact contact=contactsBookSPhones.get(user.phone);
      if (contact != null) {
        int index=contact.shortPhones.indexOf(user.phone);
        if (index != -1) {
          contact.phoneDeleted.set(index,1);
        }
      }
      if (toDelete.length() != 0) {
        toDelete.append(",");
      }
      toDelete.append(user.phone);
    }
  }
  if (toAdd.length() != 0 || toDelete.length() != 0) {
    MessagesStorage.getInstance(currentAccount).applyPhoneBookUpdates(toAdd.toString(),toDelete.toString());
  }
  if (reloadContacts) {
    Utilities.stageQueue.postRunnable(() -> loadContacts(false,0));
  }
 else {
    final ArrayList<TLRPC.TL_contact> newContacts=newC;
    final ArrayList<Integer> contactsToDelete=contactsTD;
    AndroidUtilities.runOnUIThread(() -> {
      for (int a=0; a < newContacts.size(); a++) {
        TLRPC.TL_contact contact=newContacts.get(a);
        if (contactsDict.get(contact.user_id) == null) {
          contacts.add(contact);
          contactsDict.put(contact.user_id,contact);
        }
      }
      for (int a=0; a < contactsToDelete.size(); a++) {
        Integer uid=contactsToDelete.get(a);
        TLRPC.TL_contact contact=contactsDict.get(uid);
        if (contact != null) {
          contacts.remove(contact);
          contactsDict.remove(uid);
        }
      }
      if (!newContacts.isEmpty()) {
        updateUnregisteredContacts();
        performWriteContactsToPhoneBook();
      }
      performSyncPhoneBook(getContactsCopy(contactsBook),false,false,false,false,true,false);
      buildContactsSectionsArrays(!newContacts.isEmpty());
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsDidLoad);
    }
);
  }
}
