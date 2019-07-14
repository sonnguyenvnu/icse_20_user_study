public boolean isDialogMuted(long dialog_id){
  int mute_type=notificationsPreferences.getInt("notify2_" + dialog_id,-1);
  if (mute_type == -1) {
    return !NotificationsController.getInstance(currentAccount).isGlobalNotificationsEnabled(dialog_id);
  }
  if (mute_type == 2) {
    return true;
  }
 else   if (mute_type == 3) {
    int mute_until=notificationsPreferences.getInt("notifyuntil_" + dialog_id,0);
    if (mute_until >= ConnectionsManager.getInstance(currentAccount).getCurrentTime()) {
      return true;
    }
  }
  return false;
}
