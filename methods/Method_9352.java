@Override public boolean onFragmentCreate(){
  MessagesController.getInstance(currentAccount).loadSignUpNotificationsSettings();
  loadExceptions();
  if (UserConfig.getActivatedAccountsCount() > 1) {
    accountsSectionRow=rowCount++;
    accountsAllRow=rowCount++;
    accountsInfoRow=rowCount++;
  }
 else {
    accountsSectionRow=-1;
    accountsAllRow=-1;
    accountsInfoRow=-1;
  }
  notificationsSectionRow=rowCount++;
  privateRow=rowCount++;
  groupRow=rowCount++;
  channelsRow=rowCount++;
  notificationsSection2Row=rowCount++;
  callsSectionRow=rowCount++;
  callsVibrateRow=rowCount++;
  callsRingtoneRow=rowCount++;
  eventsSection2Row=rowCount++;
  badgeNumberSection=rowCount++;
  badgeNumberShowRow=rowCount++;
  badgeNumberMutedRow=rowCount++;
  badgeNumberMessagesRow=rowCount++;
  badgeNumberSection2Row=rowCount++;
  inappSectionRow=rowCount++;
  inappSoundRow=rowCount++;
  inappVibrateRow=rowCount++;
  inappPreviewRow=rowCount++;
  inchatSoundRow=rowCount++;
  if (Build.VERSION.SDK_INT >= 21) {
    inappPriorityRow=rowCount++;
  }
 else {
    inappPriorityRow=-1;
  }
  callsSection2Row=rowCount++;
  eventsSectionRow=rowCount++;
  contactJoinedRow=rowCount++;
  pinnedMessageRow=rowCount++;
  otherSection2Row=rowCount++;
  otherSectionRow=rowCount++;
  notificationsServiceRow=rowCount++;
  notificationsServiceConnectionRow=rowCount++;
  androidAutoAlertRow=-1;
  repeatRow=rowCount++;
  resetSection2Row=rowCount++;
  resetSectionRow=rowCount++;
  resetNotificationsRow=rowCount++;
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.notificationsSettingsUpdated);
  return super.onFragmentCreate();
}
