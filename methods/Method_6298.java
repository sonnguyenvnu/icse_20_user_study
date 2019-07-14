public void deleteAllContacts(final Runnable runnable){
  resetImportedContacts();
  TLRPC.TL_contacts_deleteContacts req=new TLRPC.TL_contacts_deleteContacts();
  for (int a=0, size=contacts.size(); a < size; a++) {
    TLRPC.TL_contact contact=contacts.get(a);
    req.id.add(MessagesController.getInstance(currentAccount).getInputUser(contact.user_id));
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response instanceof TLRPC.TL_boolTrue) {
      contactsBookSPhones.clear();
      contactsBook.clear();
      completedRequestsCount=0;
      migratingContacts=false;
      contactsSyncInProgress=false;
      contactsLoaded=false;
      loadingContacts=false;
      contactsBookLoaded=false;
      lastContactsVersions="";
      AndroidUtilities.runOnUIThread(() -> {
        AccountManager am=AccountManager.get(ApplicationLoader.applicationContext);
        try {
          Account[] accounts=am.getAccountsByType("org.telegram.messenger");
          systemAccount=null;
          for (int a=0; a < accounts.length; a++) {
            Account acc=accounts[a];
            for (int b=0; b < UserConfig.MAX_ACCOUNT_COUNT; b++) {
              TLRPC.User user=UserConfig.getInstance(b).getCurrentUser();
              if (user != null) {
                if (acc.name.equals("" + user.id)) {
                  am.removeAccount(acc,null,null);
                  break;
                }
              }
            }
          }
        }
 catch (        Throwable ignore) {
        }
        try {
          systemAccount=new Account("" + UserConfig.getInstance(currentAccount).getClientUserId(),"org.telegram.messenger");
          am.addAccountExplicitly(systemAccount,"",null);
        }
 catch (        Exception ignore) {
        }
        MessagesStorage.getInstance(currentAccount).putCachedPhoneBook(new HashMap<>(),false,true);
        MessagesStorage.getInstance(currentAccount).putContacts(new ArrayList<>(),true);
        phoneBookContacts.clear();
        contacts.clear();
        contactsDict.clear();
        usersSectionsDict.clear();
        usersMutualSectionsDict.clear();
        sortedUsersSectionsArray.clear();
        phoneBookSectionsDict.clear();
        phoneBookSectionsArray.clear();
        delayedContactsUpdate.clear();
        sortedUsersMutualSectionsArray.clear();
        contactsByPhone.clear();
        contactsByShortPhone.clear();
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.contactsDidLoad);
        loadContacts(false,0);
        runnable.run();
      }
);
    }
  }
);
}
