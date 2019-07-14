@Override public boolean onFragmentCreate(){
  rowCount=0;
  if (addingException) {
    avatarRow=rowCount++;
    avatarSectionRow=rowCount++;
    customRow=-1;
    customInfoRow=-1;
  }
 else {
    avatarRow=-1;
    avatarSectionRow=-1;
    customRow=rowCount++;
    customInfoRow=rowCount++;
  }
  generalRow=rowCount++;
  if (addingException) {
    enableRow=rowCount++;
  }
 else {
    enableRow=-1;
  }
  soundRow=rowCount++;
  vibrateRow=rowCount++;
  if ((int)dialog_id < 0) {
    smartRow=rowCount++;
  }
 else {
    smartRow=-1;
  }
  if (Build.VERSION.SDK_INT >= 21) {
    priorityRow=rowCount++;
  }
 else {
    priorityRow=-1;
  }
  priorityInfoRow=rowCount++;
  boolean isChannel;
  int lower_id=(int)dialog_id;
  if (lower_id < 0) {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
    isChannel=ChatObject.isChannel(chat) && !chat.megagroup;
  }
 else {
    isChannel=false;
  }
  if (lower_id != 0 && !isChannel) {
    popupRow=rowCount++;
    popupEnabledRow=rowCount++;
    popupDisabledRow=rowCount++;
    popupInfoRow=rowCount++;
  }
 else {
    popupRow=-1;
    popupEnabledRow=-1;
    popupDisabledRow=-1;
    popupInfoRow=-1;
  }
  if (lower_id > 0) {
    callsRow=rowCount++;
    callsVibrateRow=rowCount++;
    ringtoneRow=rowCount++;
    ringtoneInfoRow=rowCount++;
  }
 else {
    callsRow=-1;
    callsVibrateRow=-1;
    ringtoneRow=-1;
    ringtoneInfoRow=-1;
  }
  ledRow=rowCount++;
  colorRow=rowCount++;
  ledInfoRow=rowCount++;
  SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
  customEnabled=preferences.getBoolean("custom_" + dialog_id,false) || addingException;
  boolean hasOverride=preferences.contains("notify2_" + dialog_id);
  int value=preferences.getInt("notify2_" + dialog_id,0);
  if (value == 0) {
    if (hasOverride) {
      notificationsEnabled=true;
    }
 else {
      notificationsEnabled=NotificationsController.getInstance(currentAccount).isGlobalNotificationsEnabled(dialog_id);
    }
  }
 else   if (value == 1) {
    notificationsEnabled=true;
  }
 else   if (value == 2) {
    notificationsEnabled=false;
  }
 else {
    notificationsEnabled=false;
  }
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.notificationsSettingsUpdated);
  return super.onFragmentCreate();
}
