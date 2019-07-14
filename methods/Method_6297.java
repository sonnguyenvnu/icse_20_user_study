public void syncPhoneBookByAlert(final HashMap<String,Contact> contacts,final boolean first,final boolean schedule,final boolean cancel){
  Utilities.globalQueue.postRunnable(() -> {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("sync contacts by alert");
    }
    performSyncPhoneBook(contacts,true,first,schedule,false,false,cancel);
  }
);
}
