public void readContacts(){
synchronized (loadContactsSync) {
    if (loadingContacts) {
      return;
    }
    loadingContacts=true;
  }
  Utilities.stageQueue.postRunnable(() -> {
    if (!contacts.isEmpty() || contactsLoaded) {
synchronized (loadContactsSync) {
        loadingContacts=false;
      }
      return;
    }
    loadContacts(true,0);
  }
);
}
