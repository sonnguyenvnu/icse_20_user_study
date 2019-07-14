private void toggleMute(boolean instant){
  boolean muted=MessagesController.getInstance(currentAccount).isDialogMuted(dialog_id);
  if (!muted) {
    if (instant) {
      long flags;
      SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
      SharedPreferences.Editor editor=preferences.edit();
      editor.putInt("notify2_" + dialog_id,2);
      flags=1;
      MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,flags);
      editor.commit();
      TLRPC.Dialog dialog=MessagesController.getInstance(currentAccount).dialogs_dict.get(dialog_id);
      if (dialog != null) {
        dialog.notify_settings=new TLRPC.TL_peerNotifySettings();
        dialog.notify_settings.mute_until=Integer.MAX_VALUE;
      }
      NotificationsController.getInstance(currentAccount).updateServerNotificationsSettings(dialog_id);
      NotificationsController.getInstance(currentAccount).removeNotificationsForDialog(dialog_id);
    }
 else {
      showDialog(AlertsCreator.createMuteAlert(getParentActivity(),dialog_id));
    }
  }
 else {
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    SharedPreferences.Editor editor=preferences.edit();
    editor.putInt("notify2_" + dialog_id,0);
    MessagesStorage.getInstance(currentAccount).setDialogFlags(dialog_id,0);
    editor.commit();
    TLRPC.Dialog dialog=MessagesController.getInstance(currentAccount).dialogs_dict.get(dialog_id);
    if (dialog != null) {
      dialog.notify_settings=new TLRPC.TL_peerNotifySettings();
    }
    NotificationsController.getInstance(currentAccount).updateServerNotificationsSettings(dialog_id);
  }
}
