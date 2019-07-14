public void loadSignUpNotificationsSettings(){
  if (!loadingNotificationSignUpSettings) {
    loadingNotificationSignUpSettings=true;
    TLRPC.TL_account_getContactSignUpNotification req=new TLRPC.TL_account_getContactSignUpNotification();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      loadingNotificationSignUpSettings=false;
      SharedPreferences.Editor editor=notificationsPreferences.edit();
      enableJoined=response instanceof TLRPC.TL_boolFalse;
      editor.putBoolean("EnableContactJoined",enableJoined);
      editor.commit();
      UserConfig.getInstance(currentAccount).notificationsSignUpSettingsLoaded=true;
      UserConfig.getInstance(currentAccount).saveConfig(false);
    }
));
  }
}
