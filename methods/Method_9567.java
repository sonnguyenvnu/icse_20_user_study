private void updateRows(){
  rowCount=0;
  privacySectionRow=rowCount++;
  blockedRow=rowCount++;
  phoneNumberRow=rowCount++;
  lastSeenRow=rowCount++;
  profilePhotoRow=rowCount++;
  forwardsRow=rowCount++;
  callsRow=rowCount++;
  groupsRow=rowCount++;
  groupsDetailRow=rowCount++;
  securitySectionRow=rowCount++;
  passcodeRow=rowCount++;
  passwordRow=rowCount++;
  sessionsRow=rowCount++;
  sessionsDetailRow=rowCount++;
  advancedSectionRow=rowCount++;
  clearDraftsRow=rowCount++;
  deleteAccountRow=rowCount++;
  deleteAccountDetailRow=rowCount++;
  botsSectionRow=rowCount++;
  if (UserConfig.getInstance(currentAccount).hasSecureData) {
    passportRow=rowCount++;
  }
 else {
    passportRow=-1;
  }
  paymentsClearRow=rowCount++;
  webSessionsRow=rowCount++;
  botsDetailRow=rowCount++;
  contactsSectionRow=rowCount++;
  contactsDeleteRow=rowCount++;
  contactsSyncRow=rowCount++;
  contactsSuggestRow=rowCount++;
  contactsDetailRow=rowCount++;
  secretSectionRow=rowCount++;
  secretMapRow=rowCount++;
  secretWebpageRow=rowCount++;
  secretDetailRow=rowCount++;
  if (listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
}
