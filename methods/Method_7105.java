private int getNotifyOverride(SharedPreferences preferences,long dialog_id){
  int notifyOverride=preferences.getInt("notify2_" + dialog_id,-1);
  if (notifyOverride == 3) {
    int muteUntil=preferences.getInt("notifyuntil_" + dialog_id,0);
    if (muteUntil >= ConnectionsManager.getInstance(currentAccount).getCurrentTime()) {
      notifyOverride=2;
    }
  }
  return notifyOverride;
}
