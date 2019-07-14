public void processLoadedContacts(final ArrayList<TLRPC.TL_contact> contactsArr,final ArrayList<TLRPC.User> usersArr,final int from){
  AndroidUtilities.runOnUIThread(() -> {
    MessagesController.getInstance(currentAccount).putUsers(usersArr,from == 1);
    final SparseArray<TLRPC.User> usersDict=new SparseArray<>();
    final boolean isEmpty=contactsArr.isEmpty();
    if (!contacts.isEmpty()) {
      for (int a=0; a < contactsArr.size(); a++) {
        TLRPC.TL_contact contact=contactsArr.get(a);
        if (contactsDict.get(contact.user_id) != null) {
          contactsArr.remove(a);
          a--;
        }
      }
      contactsArr.addAll(contacts);
    }
    for (int a=0; a < contactsArr.size(); a++) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(contactsArr.get(a).user_id);
      if (user != null) {
        usersDict.put(user.id,user);
      }
    }
    Utilities.stageQueue.postRunnable(() -> {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("done loading contacts");
      }
      if (from == 1 && (contactsArr.isEmpty() || Math.abs(System.currentTimeMillis() / 1000 - UserConfig.getInstance(currentAccount).lastContactsSyncTime) >= 24 * 60 * 60)) {
        loadContacts(false,getContactsHash(contactsArr));
        if (contactsArr.isEmpty()) {
          return;
        }
      }
      if (from == 0) {
        UserConfig.getInstance(currentAccount).lastContactsSyncTime=(int)(System.currentTimeMillis() / 1000);
        UserConfig.getInstance(currentAccount).saveConfig(false);
      }
      for (int a=0; a < contactsArr.size(); a++) {
        TLRPC.TL_contact contact=contactsArr.get(a);
        if (usersDict.get(contact.user_id) == null && contact.user_id != UserConfig.getInstance(currentAccount).getClientUserId()) {
          loadContacts(false,0);
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("contacts are broken, load from server");
          }
          return;
        }
      }
      if (from != 1) {
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(usersArr,null,true,true);
        MessagesStorage.getInstance(currentAccount).putContacts(contactsArr,from != 2);
      }
      Collections.sort(contactsArr,(tl_contact,tl_contact2) -> {
        TLRPC.User user1=usersDict.get(tl_contact.user_id);
        TLRPC.User user2=usersDict.get(tl_contact2.user_id);
        String name1=UserObject.getFirstName(user1);
        String name2=UserObject.getFirstName(user2);
        return name1.compareTo(name2);
      }
);
      final ConcurrentHashMap<Integer,TLRPC.TL_contact> contactsDictionary=new ConcurrentHashMap<>(20,1.0f,2);
      final HashMap<String,ArrayList<TLRPC.TL_contact>> sectionsDict=new HashMap<>();
      final HashMap<String,ArrayList<TLRPC.TL_contact>> sectionsDictMutual=new HashMap<>();
      final ArrayList<String> sortedSectionsArray=new ArrayList<>();
      final ArrayList<String> sortedSectionsArrayMutual=new ArrayList<>();
      HashMap<String,TLRPC.TL_contact> contactsByPhonesDict=null;
      HashMap<String,TLRPC.TL_contact> contactsByPhonesShortDict=null;
      if (!contactsBookLoaded) {
        contactsByPhonesDict=new HashMap<>();
        contactsByPhonesShortDict=new HashMap<>();
      }
      final HashMap<String,TLRPC.TL_contact> contactsByPhonesDictFinal=contactsByPhonesDict;
      final HashMap<String,TLRPC.TL_contact> contactsByPhonesShortDictFinal=contactsByPhonesShortDict;
      for (int a=0; a < contactsArr.size(); a++) {
        TLRPC.TL_contact value=contactsArr.get(a);
        TLRPC.User user=usersDict.get(value.user_id);
        if (user == null) {
          continue;
        }
        contactsDictionary.put(value.user_id,value);
        if (contactsByPhonesDict != null && !TextUtils.isEmpty(user.phone)) {
          contactsByPhonesDict.put(user.phone,value);
          contactsByPhonesShortDict.put(user.phone.substring(Math.max(0,user.phone.length() - 7)),value);
        }
        String key=UserObject.getFirstName(user);
        if (key.length() > 1) {
          key=key.substring(0,1);
        }
        if (key.length() == 0) {
          key="#";
        }
 else {
          key=key.toUpperCase();
        }
        String replace=sectionsToReplace.get(key);
        if (replace != null) {
          key=replace;
        }
        ArrayList<TLRPC.TL_contact> arr=sectionsDict.get(key);
        if (arr == null) {
          arr=new ArrayList<>();
          sectionsDict.put(key,arr);
          sortedSectionsArray.add(key);
        }
        arr.add(value);
        if (user.mutual_contact) {
          arr=sectionsDictMutual.get(key);
          if (arr == null) {
            arr=new ArrayList<>();
            sectionsDictMutual.put(key,arr);
            sortedSectionsArrayMutual.add(key);
          }
          arr.add(value);
        }
      }
      Collections.sort(sortedSectionsArray,(s,s2) -> {
        char cv1=s.charAt(0);
        char cv2=s2.charAt(0);
        if (cv1 == '#') {
          return 1;
        }
 else         if (cv2 == '#') {
          return -1;
        }
        return s.compareTo(s2);
      }
);
      Collections.sort(sortedSectionsArrayMutual,(s,s2) -> {
        char cv1=s.charAt(0);
        char cv2=s2.charAt(0);
        if (cv1 == '#') {
          return 1;
        }
 else         if (cv2 == '#') {
          return -1;
        }
        return s.compareTo(s2);
      }
);
      AndroidUtilities.runOnUIThread(() -> {
        contacts=contactsArr;
        contactsDict=contactsDictionary;
        usersSectionsDict=sectionsDict;
        usersMutualSectionsDict=sectionsDictMutual;
        sortedUsersSectionsArray=sortedSectionsArray;
        sortedUsersMutualSectionsArray=sortedSectionsArrayMutual;
        if (from != 2) {
synchronized (loadContactsSync) {
            loadingContacts=false;
          }
        }
        performWriteContactsToPhoneBook();
        updateUnregisteredContacts();
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsDidLoad);
        if (from != 1 && !isEmpty) {
          saveContactsLoadTime();
        }
 else {
          reloadContactsStatusesMaybe();
        }
      }
);
      if (!delayedContactsUpdate.isEmpty() && contactsLoaded && contactsBookLoaded) {
        applyContactsUpdates(delayedContactsUpdate,null,null,null);
        delayedContactsUpdate.clear();
      }
      if (contactsByPhonesDictFinal != null) {
        AndroidUtilities.runOnUIThread(() -> {
          Utilities.globalQueue.postRunnable(() -> {
            contactsByPhone=contactsByPhonesDictFinal;
            contactsByShortPhone=contactsByPhonesShortDictFinal;
          }
);
          if (contactsSyncInProgress) {
            return;
          }
          contactsSyncInProgress=true;
          MessagesStorage.getInstance(currentAccount).getCachedPhoneBook(false);
        }
);
      }
 else {
        contactsLoaded=true;
      }
    }
);
  }
);
}
