public boolean isLoadingContacts(){
synchronized (loadContactsSync) {
    return loadingContacts;
  }
}
