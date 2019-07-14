public void loadDrafts(){
  if (UserConfig.getInstance(currentAccount).draftsLoaded || loadingDrafts) {
    return;
  }
  loadingDrafts=true;
  TLRPC.TL_messages_getAllDrafts req=new TLRPC.TL_messages_getAllDrafts();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null) {
      return;
    }
    MessagesController.getInstance(currentAccount).processUpdates((TLRPC.Updates)response,false);
    AndroidUtilities.runOnUIThread(() -> {
      UserConfig.getInstance(currentAccount).draftsLoaded=true;
      loadingDrafts=false;
      UserConfig.getInstance(currentAccount).saveConfig(false);
    }
);
  }
);
}
