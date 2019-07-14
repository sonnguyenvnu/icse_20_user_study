public void loadGlobalNotificationsSettings(){
  if (loadingNotificationSettings == 0 && !UserConfig.getInstance(currentAccount).notificationsSettingsLoaded) {
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    SharedPreferences.Editor editor1=null;
    if (preferences.contains("EnableGroup")) {
      boolean enabled=preferences.getBoolean("EnableGroup",true);
      if (editor1 == null) {
        editor1=preferences.edit();
      }
      if (!enabled) {
        editor1.putInt("EnableGroup2",Integer.MAX_VALUE);
        editor1.putInt("EnableChannel2",Integer.MAX_VALUE);
      }
      editor1.remove("EnableGroup").commit();
    }
    if (preferences.contains("EnableAll")) {
      boolean enabled=preferences.getBoolean("EnableAll",true);
      if (editor1 == null) {
        editor1=preferences.edit();
      }
      if (!enabled) {
        editor1.putInt("EnableAll2",Integer.MAX_VALUE);
      }
      editor1.remove("EnableAll").commit();
    }
    if (editor1 != null) {
      editor1.commit();
    }
    loadingNotificationSettings=3;
    for (int a=0; a < 3; a++) {
      TLRPC.TL_account_getNotifySettings req=new TLRPC.TL_account_getNotifySettings();
      if (a == 0) {
        req.peer=new TLRPC.TL_inputNotifyChats();
      }
 else       if (a == 1) {
        req.peer=new TLRPC.TL_inputNotifyUsers();
      }
 else       if (a == 2) {
        req.peer=new TLRPC.TL_inputNotifyBroadcasts();
      }
      final int type=a;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        if (response != null) {
          loadingNotificationSettings--;
          TLRPC.TL_peerNotifySettings notify_settings=(TLRPC.TL_peerNotifySettings)response;
          SharedPreferences.Editor editor=notificationsPreferences.edit();
          if (type == 0) {
            if ((notify_settings.flags & 1) != 0) {
              editor.putBoolean("EnablePreviewGroup",notify_settings.show_previews);
            }
            if ((notify_settings.flags & 2) != 0) {
            }
            if ((notify_settings.flags & 4) != 0) {
              editor.putInt("EnableGroup2",notify_settings.mute_until);
            }
          }
 else           if (type == 1) {
            if ((notify_settings.flags & 1) != 0) {
              editor.putBoolean("EnablePreviewAll",notify_settings.show_previews);
            }
            if ((notify_settings.flags & 2) != 0) {
            }
            if ((notify_settings.flags & 4) != 0) {
              editor.putInt("EnableAll2",notify_settings.mute_until);
            }
          }
 else           if (type == 2) {
            if ((notify_settings.flags & 1) != 0) {
              editor.putBoolean("EnablePreviewChannel",notify_settings.show_previews);
            }
            if ((notify_settings.flags & 2) != 0) {
            }
            if ((notify_settings.flags & 4) != 0) {
              editor.putInt("EnableChannel2",notify_settings.mute_until);
            }
          }
          editor.commit();
          if (loadingNotificationSettings == 0) {
            UserConfig.getInstance(currentAccount).notificationsSettingsLoaded=true;
            UserConfig.getInstance(currentAccount).saveConfig(false);
          }
        }
      }
));
    }
  }
  if (!UserConfig.getInstance(currentAccount).notificationsSignUpSettingsLoaded) {
    loadSignUpNotificationsSettings();
  }
}
