public void setDialogNotificationsSettings(long dialog_id,int setting){
  SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
  SharedPreferences.Editor editor=preferences.edit();
  TLRPC.Dialog dialog=MessagesController.getInstance(UserConfig.selectedAccount).dialogs_dict.get(dialog_id);
  if (setting == SETTING_MUTE_UNMUTE) {
    boolean defaultEnabled=NotificationsController.getInstance(currentAccount).isGlobalNotificationsEnabled(dialog_id);
    if (defaultEnabled) {
      editor.remove("notify2_" + dialog_id);
    }
 else {
      editor.putInt("notify2_" + dialog_id,0);
    }
    MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,0);
    if (dialog != null) {
      dialog.notify_settings=new TLRPC.TL_peerNotifySettings();
    }
  }
 else {
    int untilTime=ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
    if (setting == SETTING_MUTE_HOUR) {
      untilTime+=60 * 60;
    }
 else     if (setting == SETTING_MUTE_8_HOURS) {
      untilTime+=60 * 60 * 8;
    }
 else     if (setting == SETTING_MUTE_2_DAYS) {
      untilTime+=60 * 60 * 48;
    }
 else     if (setting == SETTING_MUTE_FOREVER) {
      untilTime=Integer.MAX_VALUE;
    }
    long flags;
    if (setting == SETTING_MUTE_FOREVER) {
      editor.putInt("notify2_" + dialog_id,2);
      flags=1;
    }
 else {
      editor.putInt("notify2_" + dialog_id,3);
      editor.putInt("notifyuntil_" + dialog_id,untilTime);
      flags=((long)untilTime << 32) | 1;
    }
    NotificationsController.getInstance(UserConfig.selectedAccount).removeNotificationsForDialog(dialog_id);
    MessagesStorage.getInstance(UserConfig.selectedAccount).setDialogFlags(dialog_id,flags);
    if (dialog != null) {
      dialog.notify_settings=new TLRPC.TL_peerNotifySettings();
      dialog.notify_settings.mute_until=untilTime;
    }
  }
  editor.commit();
  updateServerNotificationsSettings(dialog_id);
}
