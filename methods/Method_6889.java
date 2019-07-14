private void applyDialogsNotificationsSettings(ArrayList<TLRPC.Dialog> dialogs){
  SharedPreferences.Editor editor=null;
  for (int a=0; a < dialogs.size(); a++) {
    TLRPC.Dialog dialog=dialogs.get(a);
    if (dialog.peer != null && dialog.notify_settings instanceof TLRPC.TL_peerNotifySettings) {
      if (editor == null) {
        editor=notificationsPreferences.edit();
      }
      int dialog_id;
      if (dialog.peer.user_id != 0) {
        dialog_id=dialog.peer.user_id;
      }
 else       if (dialog.peer.chat_id != 0) {
        dialog_id=-dialog.peer.chat_id;
      }
 else {
        dialog_id=-dialog.peer.channel_id;
      }
      if ((dialog.notify_settings.flags & 2) != 0) {
        editor.putBoolean("silent_" + dialog_id,dialog.notify_settings.silent);
      }
 else {
        editor.remove("silent_" + dialog_id);
      }
      if ((dialog.notify_settings.flags & 4) != 0) {
        if (dialog.notify_settings.mute_until > ConnectionsManager.getInstance(currentAccount).getCurrentTime()) {
          if (dialog.notify_settings.mute_until > ConnectionsManager.getInstance(currentAccount).getCurrentTime() + 60 * 60 * 24 * 365) {
            editor.putInt("notify2_" + dialog_id,2);
            dialog.notify_settings.mute_until=Integer.MAX_VALUE;
          }
 else {
            editor.putInt("notify2_" + dialog_id,3);
            editor.putInt("notifyuntil_" + dialog_id,dialog.notify_settings.mute_until);
          }
        }
 else {
          editor.putInt("notify2_" + dialog_id,0);
        }
      }
 else {
        editor.remove("notify2_" + dialog_id);
      }
    }
  }
  if (editor != null) {
    editor.commit();
  }
}
