public void checkContacts(){
  Utilities.globalQueue.postRunnable(() -> {
    if (checkContactsInternal()) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("detected contacts change");
      }
      performSyncPhoneBook(getContactsCopy(contactsBook),true,false,true,false,true,false);
    }
  }
);
}
