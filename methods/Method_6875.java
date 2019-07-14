public void loadHintDialogs(){
  if (!hintDialogs.isEmpty() || TextUtils.isEmpty(installReferer)) {
    return;
  }
  TLRPC.TL_help_getRecentMeUrls req=new TLRPC.TL_help_getRecentMeUrls();
  req.referer=installReferer;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      AndroidUtilities.runOnUIThread(() -> {
        TLRPC.TL_help_recentMeUrls res=(TLRPC.TL_help_recentMeUrls)response;
        putUsers(res.users,false);
        putChats(res.chats,false);
        hintDialogs.clear();
        hintDialogs.addAll(res.urls);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
      }
);
    }
  }
);
}
