private void applyDialogNotificationsSettings(long dialog_id,TLRPC.PeerNotifySettings notify_settings){
  if (notify_settings == null) {
    return;
  }
  int currentValue=notificationsPreferences.getInt("notify2_" + dialog_id,-1);
  int currentValue2=notificationsPreferences.getInt("notifyuntil_" + dialog_id,0);
  SharedPreferences.Editor editor=notificationsPreferences.edit();
  boolean updated=false;
  TLRPC.Dialog dialog=dialogs_dict.get(dialog_id);
  if (dialog != null) {
    dialog.notify_settings=notify_settings;
  }
  if ((notify_settings.flags & 2) != 0) {
    editor.putBoolean("silent_" + dialog_id,notify_settings.silent);
  }
 else {
    editor.remove("silent_" + dialog_id);
  }
  if ((notify_settings.flags & 4) != 0) {
    if (notify_settings.mute_until > ConnectionsManager.getInstance(currentAccount).getCurrentTime()) {
      int until=0;
      if (notify_settings.mute_until > ConnectionsManager.getInstance(currentAccount).getCurrentTime() + 60 * 60 * 24 * 365) {
        if (currentValue != 2) {
          updated=true;
          editor.putInt("notify2_" + dialog_id,2);
          if (dialog != null) {
            dialog.notify_settings.mute_until=Integer.MAX_VALUE;
          }
        }
      }
 else {
        if (currentValue != 3 || currentValue2 != notify_settings.mute_until) {
          updated=true;
          editor.putInt("notify2_" + dialog_id,3);
          editor.putInt("notifyuntil_" + dialog_id,notify_settings.mute_until);
          if (dialog != null) {
            dialog.notify_settings.mute_until=until;
          }
        }
        until=notify_settings.mute_until;
      }
      MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,((long)until << 32) | 1);
      NotificationsController.getInstance(currentAccount).removeNotificationsForDialog(dialog_id);
    }
 else {
      if (currentValue != 0 && currentValue != 1) {
        updated=true;
        if (dialog != null) {
          dialog.notify_settings.mute_until=0;
        }
        editor.putInt("notify2_" + dialog_id,0);
      }
      MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,0);
    }
  }
 else {
    if (currentValue != -1) {
      updated=true;
      if (dialog != null) {
        dialog.notify_settings.mute_until=0;
      }
      editor.remove("notify2_" + dialog_id);
    }
    MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,0);
  }
  editor.commit();
  if (updated) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.notificationsSettingsUpdated);
  }
}
