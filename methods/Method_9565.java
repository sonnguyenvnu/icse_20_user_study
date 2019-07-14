@Override public void onFragmentDestroy(){
  super.onFragmentDestroy();
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.privacyRulesUpdated);
  if (currentSync != newSync) {
    UserConfig.getInstance(currentAccount).syncContacts=newSync;
    UserConfig.getInstance(currentAccount).saveConfig(false);
    if (newSync) {
      ContactsController.getInstance(currentAccount).forceImportContacts();
      if (getParentActivity() != null) {
        Toast.makeText(getParentActivity(),LocaleController.getString("SyncContactsAdded",R.string.SyncContactsAdded),Toast.LENGTH_SHORT).show();
      }
    }
  }
  if (newSuggest != currentSuggest) {
    if (!newSuggest) {
      DataQuery.getInstance(currentAccount).clearTopPeers();
    }
    UserConfig.getInstance(currentAccount).suggestContacts=newSuggest;
    UserConfig.getInstance(currentAccount).saveConfig(false);
    TLRPC.TL_contacts_toggleTopPeers req=new TLRPC.TL_contacts_toggleTopPeers();
    req.enabled=newSuggest;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
);
  }
}
