protected void performSyncPhoneBook(final HashMap<String,Contact> contactHashMap,final boolean request,final boolean first,final boolean schedule,final boolean force,final boolean checkCount,final boolean canceled){
  if (!first && !contactsBookLoaded) {
    return;
  }
  Utilities.globalQueue.postRunnable(() -> {
    int newPhonebookContacts=0;
    int serverContactsInPhonebook=0;
    boolean disableDeletion=true;
    HashMap<String,Contact> contactShortHashMap=new HashMap<>();
    for (    HashMap.Entry<String,Contact> entry : contactHashMap.entrySet()) {
      Contact c=entry.getValue();
      for (int a=0; a < c.shortPhones.size(); a++) {
        contactShortHashMap.put(c.shortPhones.get(a),c);
      }
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("start read contacts from phone");
    }
    if (!schedule) {
      checkContactsInternal();
    }
    final HashMap<String,Contact> contactsMap=readContactsFromPhoneBook();
    final HashMap<String,ArrayList<Object>> phoneBookSectionsDictFinal=new HashMap<>();
    final HashMap<String,Contact> phoneBookByShortPhonesFinal=new HashMap<>();
    final ArrayList<String> phoneBookSectionsArrayFinal=new ArrayList<>();
    for (    HashMap.Entry<String,Contact> entry : contactsMap.entrySet()) {
      Contact contact=entry.getValue();
      for (int a=0, size=contact.shortPhones.size(); a < size; a++) {
        String phone=contact.shortPhones.get(a);
        phoneBookByShortPhonesFinal.put(phone.substring(Math.max(0,phone.length() - 7)),contact);
      }
      String key=contact.getLetter();
      ArrayList<Object> arrayList=phoneBookSectionsDictFinal.get(key);
      if (arrayList == null) {
        arrayList=new ArrayList<>();
        phoneBookSectionsDictFinal.put(key,arrayList);
        phoneBookSectionsArrayFinal.add(key);
      }
      arrayList.add(contact);
    }
    final HashMap<String,Contact> contactsBookShort=new HashMap<>();
    int alreadyImportedContacts=contactHashMap.size();
    ArrayList<TLRPC.TL_inputPhoneContact> toImport=new ArrayList<>();
    if (!contactHashMap.isEmpty()) {
      for (      HashMap.Entry<String,Contact> pair : contactsMap.entrySet()) {
        String id=pair.getKey();
        Contact value=pair.getValue();
        Contact existing=contactHashMap.get(id);
        if (existing == null) {
          for (int a=0; a < value.shortPhones.size(); a++) {
            Contact c=contactShortHashMap.get(value.shortPhones.get(a));
            if (c != null) {
              existing=c;
              id=existing.key;
              break;
            }
          }
        }
        if (existing != null) {
          value.imported=existing.imported;
        }
        boolean nameChanged=existing != null && (!TextUtils.isEmpty(value.first_name) && !existing.first_name.equals(value.first_name) || !TextUtils.isEmpty(value.last_name) && !existing.last_name.equals(value.last_name));
        if (existing == null || nameChanged) {
          for (int a=0; a < value.phones.size(); a++) {
            String sphone=value.shortPhones.get(a);
            String sphone9=sphone.substring(Math.max(0,sphone.length() - 7));
            contactsBookShort.put(sphone,value);
            if (existing != null) {
              int index=existing.shortPhones.indexOf(sphone);
              if (index != -1) {
                Integer deleted=existing.phoneDeleted.get(index);
                value.phoneDeleted.set(a,deleted);
                if (deleted == 1) {
                  continue;
                }
              }
            }
            if (request) {
              if (!nameChanged) {
                if (contactsByPhone.containsKey(sphone)) {
                  serverContactsInPhonebook++;
                  continue;
                }
                newPhonebookContacts++;
              }
              TLRPC.TL_inputPhoneContact imp=new TLRPC.TL_inputPhoneContact();
              imp.client_id=value.contact_id;
              imp.client_id|=((long)a) << 32;
              imp.first_name=value.first_name;
              imp.last_name=value.last_name;
              imp.phone=value.phones.get(a);
              toImport.add(imp);
            }
          }
          if (existing != null) {
            contactHashMap.remove(id);
          }
        }
 else {
          for (int a=0; a < value.phones.size(); a++) {
            String sphone=value.shortPhones.get(a);
            String sphone9=sphone.substring(Math.max(0,sphone.length() - 7));
            contactsBookShort.put(sphone,value);
            int index=existing.shortPhones.indexOf(sphone);
            boolean emptyNameReimport=false;
            if (request) {
              TLRPC.TL_contact contact=contactsByPhone.get(sphone);
              if (contact != null) {
                TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(contact.user_id);
                if (user != null) {
                  serverContactsInPhonebook++;
                  if (TextUtils.isEmpty(user.first_name) && TextUtils.isEmpty(user.last_name) && (!TextUtils.isEmpty(value.first_name) || !TextUtils.isEmpty(value.last_name))) {
                    index=-1;
                    emptyNameReimport=true;
                  }
                }
              }
 else               if (contactsByShortPhone.containsKey(sphone9)) {
                serverContactsInPhonebook++;
              }
            }
            if (index == -1) {
              if (request) {
                if (!emptyNameReimport) {
                  TLRPC.TL_contact contact=contactsByPhone.get(sphone);
                  if (contact != null) {
                    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(contact.user_id);
                    if (user != null) {
                      serverContactsInPhonebook++;
                      String firstName=user.first_name != null ? user.first_name : "";
                      String lastName=user.last_name != null ? user.last_name : "";
                      if (firstName.equals(value.first_name) && lastName.equals(value.last_name) || TextUtils.isEmpty(value.first_name) && TextUtils.isEmpty(value.last_name)) {
                        continue;
                      }
                    }
 else {
                      newPhonebookContacts++;
                    }
                  }
 else                   if (contactsByShortPhone.containsKey(sphone9)) {
                    serverContactsInPhonebook++;
                  }
                }
                TLRPC.TL_inputPhoneContact imp=new TLRPC.TL_inputPhoneContact();
                imp.client_id=value.contact_id;
                imp.client_id|=((long)a) << 32;
                imp.first_name=value.first_name;
                imp.last_name=value.last_name;
                imp.phone=value.phones.get(a);
                toImport.add(imp);
              }
            }
 else {
              value.phoneDeleted.set(a,existing.phoneDeleted.get(index));
              existing.phones.remove(index);
              existing.shortPhones.remove(index);
              existing.phoneDeleted.remove(index);
              existing.phoneTypes.remove(index);
            }
          }
          if (existing.phones.isEmpty()) {
            contactHashMap.remove(id);
          }
        }
      }
      if (!first && contactHashMap.isEmpty() && toImport.isEmpty() && alreadyImportedContacts == contactsMap.size()) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("contacts not changed!");
        }
        return;
      }
      if (request && !contactHashMap.isEmpty() && !contactsMap.isEmpty()) {
        if (toImport.isEmpty()) {
          MessagesStorage.getInstance(currentAccount).putCachedPhoneBook(contactsMap,false,false);
        }
        if (!disableDeletion && !contactHashMap.isEmpty()) {
          AndroidUtilities.runOnUIThread(() -> {
            final ArrayList<TLRPC.User> toDelete=new ArrayList<>();
            if (contactHashMap != null && !contactHashMap.isEmpty()) {
              try {
                final HashMap<String,TLRPC.User> contactsPhonesShort=new HashMap<>();
                for (int a=0; a < contacts.size(); a++) {
                  TLRPC.TL_contact value=contacts.get(a);
                  TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(value.user_id);
                  if (user == null || TextUtils.isEmpty(user.phone)) {
                    continue;
                  }
                  contactsPhonesShort.put(user.phone,user);
                }
                int removed=0;
                for (                HashMap.Entry<String,Contact> entry : contactHashMap.entrySet()) {
                  Contact contact=entry.getValue();
                  boolean was=false;
                  for (int a=0; a < contact.shortPhones.size(); a++) {
                    String phone=contact.shortPhones.get(a);
                    TLRPC.User user=contactsPhonesShort.get(phone);
                    if (user != null) {
                      was=true;
                      toDelete.add(user);
                      contact.shortPhones.remove(a);
                      a--;
                    }
                  }
                  if (!was || contact.shortPhones.size() == 0) {
                    removed++;
                  }
                }
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
            }
            if (!toDelete.isEmpty()) {
              deleteContact(toDelete);
            }
          }
);
        }
      }
    }
 else     if (request) {
      for (      HashMap.Entry<String,Contact> pair : contactsMap.entrySet()) {
        Contact value=pair.getValue();
        String key=pair.getKey();
        for (int a=0; a < value.phones.size(); a++) {
          if (!force) {
            String sphone=value.shortPhones.get(a);
            String sphone9=sphone.substring(Math.max(0,sphone.length() - 7));
            TLRPC.TL_contact contact=contactsByPhone.get(sphone);
            if (contact != null) {
              TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(contact.user_id);
              if (user != null) {
                serverContactsInPhonebook++;
                String firstName=user.first_name != null ? user.first_name : "";
                String lastName=user.last_name != null ? user.last_name : "";
                if (firstName.equals(value.first_name) && lastName.equals(value.last_name) || TextUtils.isEmpty(value.first_name) && TextUtils.isEmpty(value.last_name)) {
                  continue;
                }
              }
            }
 else             if (contactsByShortPhone.containsKey(sphone9)) {
              serverContactsInPhonebook++;
            }
          }
          TLRPC.TL_inputPhoneContact imp=new TLRPC.TL_inputPhoneContact();
          imp.client_id=value.contact_id;
          imp.client_id|=((long)a) << 32;
          imp.first_name=value.first_name;
          imp.last_name=value.last_name;
          imp.phone=value.phones.get(a);
          toImport.add(imp);
        }
      }
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("done processing contacts");
    }
    if (request) {
      if (!toImport.isEmpty()) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.e("start import contacts");
        }
        final int checkType;
        if (checkCount && newPhonebookContacts != 0) {
          if (newPhonebookContacts >= 30) {
            checkType=1;
          }
 else           if (first && alreadyImportedContacts == 0 && contactsByPhone.size() - serverContactsInPhonebook > contactsByPhone.size() / 3 * 2) {
            checkType=2;
          }
 else {
            checkType=0;
          }
        }
 else {
          checkType=0;
        }
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("new phone book contacts " + newPhonebookContacts + " serverContactsInPhonebook " + serverContactsInPhonebook + " totalContacts " + contactsByPhone.size());
        }
        if (checkType != 0) {
          AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.hasNewContactsToImport,checkType,contactHashMap,first,schedule));
          return;
        }
 else         if (canceled) {
          Utilities.stageQueue.postRunnable(() -> {
            contactsBookSPhones=contactsBookShort;
            contactsBook=contactsMap;
            contactsSyncInProgress=false;
            contactsBookLoaded=true;
            if (first) {
              contactsLoaded=true;
            }
            if (!delayedContactsUpdate.isEmpty() && contactsLoaded) {
              applyContactsUpdates(delayedContactsUpdate,null,null,null);
              delayedContactsUpdate.clear();
            }
            MessagesStorage.getInstance(currentAccount).putCachedPhoneBook(contactsMap,false,false);
            AndroidUtilities.runOnUIThread(() -> {
              mergePhonebookAndTelegramContacts(phoneBookSectionsDictFinal,phoneBookSectionsArrayFinal,phoneBookByShortPhonesFinal);
              updateUnregisteredContacts();
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsDidLoad);
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsImported);
            }
);
          }
);
          return;
        }
        final boolean[] hasErrors=new boolean[]{false};
        final HashMap<String,Contact> contactsMapToSave=new HashMap<>(contactsMap);
        final SparseArray<String> contactIdToKey=new SparseArray<>();
        for (        HashMap.Entry<String,Contact> entry : contactsMapToSave.entrySet()) {
          Contact value=entry.getValue();
          contactIdToKey.put(value.contact_id,value.key);
        }
        completedRequestsCount=0;
        final int count=(int)Math.ceil(toImport.size() / 500.0);
        for (int a=0; a < count; a++) {
          final TLRPC.TL_contacts_importContacts req=new TLRPC.TL_contacts_importContacts();
          int start=a * 500;
          int end=Math.min(start + 500,toImport.size());
          req.contacts=new ArrayList<>(toImport.subList(start,end));
          ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
            completedRequestsCount++;
            if (error == null) {
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("contacts imported");
              }
              final TLRPC.TL_contacts_importedContacts res=(TLRPC.TL_contacts_importedContacts)response;
              if (!res.retry_contacts.isEmpty()) {
                for (int a1=0; a1 < res.retry_contacts.size(); a1++) {
                  long id=res.retry_contacts.get(a1);
                  contactsMapToSave.remove(contactIdToKey.get((int)id));
                }
                hasErrors[0]=true;
                if (BuildVars.LOGS_ENABLED) {
                  FileLog.d("result has retry contacts");
                }
              }
              for (int a1=0; a1 < res.popular_invites.size(); a1++) {
                TLRPC.TL_popularContact popularContact=res.popular_invites.get(a1);
                Contact contact=contactsMap.get(contactIdToKey.get((int)popularContact.client_id));
                if (contact != null) {
                  contact.imported=popularContact.importers;
                }
              }
              MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,null,true,true);
              ArrayList<TLRPC.TL_contact> cArr=new ArrayList<>();
              for (int a1=0; a1 < res.imported.size(); a1++) {
                TLRPC.TL_contact contact=new TLRPC.TL_contact();
                contact.user_id=res.imported.get(a1).user_id;
                cArr.add(contact);
              }
              processLoadedContacts(cArr,res.users,2);
            }
 else {
              for (int a1=0; a1 < req.contacts.size(); a1++) {
                TLRPC.TL_inputPhoneContact contact=req.contacts.get(a1);
                contactsMapToSave.remove(contactIdToKey.get((int)contact.client_id));
              }
              hasErrors[0]=true;
              if (BuildVars.LOGS_ENABLED) {
                FileLog.d("import contacts error " + error.text);
              }
            }
            if (completedRequestsCount == count) {
              if (!contactsMapToSave.isEmpty()) {
                MessagesStorage.getInstance(currentAccount).putCachedPhoneBook(contactsMapToSave,false,false);
              }
              Utilities.stageQueue.postRunnable(() -> {
                contactsBookSPhones=contactsBookShort;
                contactsBook=contactsMap;
                contactsSyncInProgress=false;
                contactsBookLoaded=true;
                if (first) {
                  contactsLoaded=true;
                }
                if (!delayedContactsUpdate.isEmpty() && contactsLoaded) {
                  applyContactsUpdates(delayedContactsUpdate,null,null,null);
                  delayedContactsUpdate.clear();
                }
                AndroidUtilities.runOnUIThread(() -> {
                  mergePhonebookAndTelegramContacts(phoneBookSectionsDictFinal,phoneBookSectionsArrayFinal,phoneBookByShortPhonesFinal);
                  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsImported);
                }
);
                if (hasErrors[0]) {
                  Utilities.globalQueue.postRunnable(() -> MessagesStorage.getInstance(currentAccount).getCachedPhoneBook(true),60000 * 5);
                }
              }
);
            }
          }
,ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagCanCompress);
        }
      }
 else {
        Utilities.stageQueue.postRunnable(() -> {
          contactsBookSPhones=contactsBookShort;
          contactsBook=contactsMap;
          contactsSyncInProgress=false;
          contactsBookLoaded=true;
          if (first) {
            contactsLoaded=true;
          }
          if (!delayedContactsUpdate.isEmpty() && contactsLoaded) {
            applyContactsUpdates(delayedContactsUpdate,null,null,null);
            delayedContactsUpdate.clear();
          }
          AndroidUtilities.runOnUIThread(() -> {
            mergePhonebookAndTelegramContacts(phoneBookSectionsDictFinal,phoneBookSectionsArrayFinal,phoneBookByShortPhonesFinal);
            updateUnregisteredContacts();
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsDidLoad);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsImported);
          }
);
        }
);
      }
    }
 else {
      Utilities.stageQueue.postRunnable(() -> {
        contactsBookSPhones=contactsBookShort;
        contactsBook=contactsMap;
        contactsSyncInProgress=false;
        contactsBookLoaded=true;
        if (first) {
          contactsLoaded=true;
        }
        if (!delayedContactsUpdate.isEmpty() && contactsLoaded && contactsBookLoaded) {
          applyContactsUpdates(delayedContactsUpdate,null,null,null);
          delayedContactsUpdate.clear();
        }
        AndroidUtilities.runOnUIThread(() -> mergePhonebookAndTelegramContacts(phoneBookSectionsDictFinal,phoneBookSectionsArrayFinal,phoneBookByShortPhonesFinal));
      }
);
      if (!contactsMap.isEmpty()) {
        MessagesStorage.getInstance(currentAccount).putCachedPhoneBook(contactsMap,false,false);
      }
    }
  }
);
}
