public void cleanup(){
  contactsBook.clear();
  contactsBookSPhones.clear();
  phoneBookContacts.clear();
  contacts.clear();
  contactsDict.clear();
  usersSectionsDict.clear();
  usersMutualSectionsDict.clear();
  sortedUsersSectionsArray.clear();
  sortedUsersMutualSectionsArray.clear();
  delayedContactsUpdate.clear();
  contactsByPhone.clear();
  contactsByShortPhone.clear();
  phoneBookSectionsDict.clear();
  phoneBookSectionsArray.clear();
  loadingContacts=false;
  contactsSyncInProgress=false;
  contactsLoaded=false;
  contactsBookLoaded=false;
  lastContactsVersions="";
  loadingDeleteInfo=0;
  deleteAccountTTL=0;
  for (int a=0; a < loadingPrivacyInfo.length; a++) {
    loadingPrivacyInfo[a]=0;
  }
  lastseenPrivacyRules=null;
  groupPrivacyRules=null;
  callPrivacyRules=null;
  p2pPrivacyRules=null;
  profilePhotoPrivacyRules=null;
  forwardsPrivacyRules=null;
  phonePrivacyRules=null;
  Utilities.globalQueue.postRunnable(() -> {
    migratingContacts=false;
    completedRequestsCount=0;
  }
);
}
