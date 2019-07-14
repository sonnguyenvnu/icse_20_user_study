public void loadPeerSettings(TLRPC.User currentUser,TLRPC.Chat currentChat){
  if (currentUser == null && currentChat == null) {
    return;
  }
  final long dialogId;
  if (currentUser != null) {
    dialogId=currentUser.id;
  }
 else {
    dialogId=-currentChat.id;
  }
  if (loadingPeerSettings.indexOfKey(dialogId) >= 0) {
    return;
  }
  loadingPeerSettings.put(dialogId,true);
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("request spam button for " + dialogId);
  }
  if (notificationsPreferences.getInt("spam3_" + dialogId,0) == 1) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("spam button already hidden for " + dialogId);
    }
    return;
  }
  boolean hidden=notificationsPreferences.getBoolean("spam_" + dialogId,false);
  if (hidden) {
    TLRPC.TL_messages_hideReportSpam req=new TLRPC.TL_messages_hideReportSpam();
    if (currentUser != null) {
      req.peer=getInputPeer(currentUser.id);
    }
 else     if (currentChat != null) {
      req.peer=getInputPeer(-currentChat.id);
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      loadingPeerSettings.remove(dialogId);
      SharedPreferences.Editor editor=notificationsPreferences.edit();
      editor.remove("spam_" + dialogId);
      editor.putInt("spam3_" + dialogId,1);
      editor.commit();
    }
));
    return;
  }
  TLRPC.TL_messages_getPeerSettings req=new TLRPC.TL_messages_getPeerSettings();
  if (currentUser != null) {
    req.peer=getInputPeer(currentUser.id);
  }
 else   if (currentChat != null) {
    req.peer=getInputPeer(-currentChat.id);
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    loadingPeerSettings.remove(dialogId);
    if (response != null) {
      TLRPC.TL_peerSettings res=(TLRPC.TL_peerSettings)response;
      SharedPreferences.Editor editor=notificationsPreferences.edit();
      if (!res.report_spam) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("don't show spam button for " + dialogId);
        }
        editor.putInt("spam3_" + dialogId,1);
        editor.commit();
      }
 else {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("show spam button for " + dialogId);
        }
        editor.putInt("spam3_" + dialogId,2);
        editor.commit();
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.peerSettingsDidLoad,dialogId);
      }
    }
  }
));
}
