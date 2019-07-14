public void processContactsUpdates(ArrayList<Integer> ids,ConcurrentHashMap<Integer,TLRPC.User> userDict){
  final ArrayList<TLRPC.TL_contact> newContacts=new ArrayList<>();
  final ArrayList<Integer> contactsToDelete=new ArrayList<>();
  for (  Integer uid : ids) {
    if (uid > 0) {
      TLRPC.TL_contact contact=new TLRPC.TL_contact();
      contact.user_id=uid;
      newContacts.add(contact);
      if (!delayedContactsUpdate.isEmpty()) {
        int idx=delayedContactsUpdate.indexOf(-uid);
        if (idx != -1) {
          delayedContactsUpdate.remove(idx);
        }
      }
    }
 else     if (uid < 0) {
      contactsToDelete.add(-uid);
      if (!delayedContactsUpdate.isEmpty()) {
        int idx=delayedContactsUpdate.indexOf(-uid);
        if (idx != -1) {
          delayedContactsUpdate.remove(idx);
        }
      }
    }
  }
  if (!contactsToDelete.isEmpty()) {
    MessagesStorage.getInstance(currentAccount).deleteContacts(contactsToDelete);
  }
  if (!newContacts.isEmpty()) {
    MessagesStorage.getInstance(currentAccount).putContacts(newContacts,false);
  }
  if (!contactsLoaded || !contactsBookLoaded) {
    delayedContactsUpdate.addAll(ids);
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("delay update - contacts add = " + newContacts.size() + " delete = " + contactsToDelete.size());
    }
  }
 else {
    applyContactsUpdates(ids,userDict,newContacts,contactsToDelete);
  }
}
