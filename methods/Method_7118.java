public void updateServerNotificationsSettings(int type){
  SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
  TLRPC.TL_account_updateNotifySettings req=new TLRPC.TL_account_updateNotifySettings();
  req.settings=new TLRPC.TL_inputPeerNotifySettings();
  req.settings.flags=5;
  if (type == TYPE_GROUP) {
    req.peer=new TLRPC.TL_inputNotifyChats();
    req.settings.mute_until=preferences.getInt("EnableGroup2",0);
    req.settings.show_previews=preferences.getBoolean("EnablePreviewGroup",true);
  }
 else   if (type == TYPE_PRIVATE) {
    req.peer=new TLRPC.TL_inputNotifyUsers();
    req.settings.mute_until=preferences.getInt("EnableAll2",0);
    req.settings.show_previews=preferences.getBoolean("EnablePreviewAll",true);
  }
 else {
    req.peer=new TLRPC.TL_inputNotifyBroadcasts();
    req.settings.mute_until=preferences.getInt("EnableChannel2",0);
    req.settings.show_previews=preferences.getBoolean("EnablePreviewChannel",true);
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
  }
);
}
