public void deleteContact(final ArrayList<TLRPC.User> users){
  if (users == null || users.isEmpty()) {
    return;
  }
  TLRPC.TL_contacts_deleteContacts req=new TLRPC.TL_contacts_deleteContacts();
  final ArrayList<Integer> uids=new ArrayList<>();
  for (  TLRPC.User user : users) {
    TLRPC.InputUser inputUser=MessagesController.getInstance(currentAccount).getInputUser(user);
    if (inputUser == null) {
      continue;
    }
    uids.add(user.id);
    req.id.add(inputUser);
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null) {
      return;
    }
    MessagesStorage.getInstance(currentAccount).deleteContacts(uids);
    Utilities.phoneBookQueue.postRunnable(() -> {
      for (      TLRPC.User user : users) {
        deleteContactFromPhoneBook(user.id);
      }
    }
);
    for (int a=0; a < users.size(); a++) {
      TLRPC.User user=users.get(a);
      if (TextUtils.isEmpty(user.phone)) {
        continue;
      }
      CharSequence name=UserObject.getUserName(user);
      MessagesStorage.getInstance(currentAccount).applyPhoneBookUpdates(user.phone,"");
      Contact contact=contactsBookSPhones.get(user.phone);
      if (contact != null) {
        int index=contact.shortPhones.indexOf(user.phone);
        if (index != -1) {
          contact.phoneDeleted.set(index,1);
        }
      }
    }
    AndroidUtilities.runOnUIThread(() -> {
      boolean remove=false;
      for (      TLRPC.User user : users) {
        TLRPC.TL_contact contact=contactsDict.get(user.id);
        if (contact != null) {
          remove=true;
          contacts.remove(contact);
          contactsDict.remove(user.id);
        }
      }
      if (remove) {
        buildContactsSectionsArrays(false);
      }
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,MessagesController.UPDATE_MASK_NAME);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsDidLoad);
    }
);
  }
);
}
