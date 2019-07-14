public void resetImportedContacts(){
  TLRPC.TL_contacts_resetSaved req=new TLRPC.TL_contacts_resetSaved();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
  }
);
}
