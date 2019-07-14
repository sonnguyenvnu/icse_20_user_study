private void reloadStats(String params){
  if (loadStats) {
    return;
  }
  loadStats=true;
  TLRPC.TL_messages_getStatsURL req=new TLRPC.TL_messages_getStatsURL();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)currentDialogId);
  req.params=params != null ? params : "";
  req.dark=Theme.getCurrentTheme().isDark();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    loadStats=false;
    if (response != null) {
      TLRPC.TL_statsURL url=(TLRPC.TL_statsURL)response;
      webView.loadUrl(currentUrl=url.url);
    }
  }
));
}
