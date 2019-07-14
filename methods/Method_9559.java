private void updateRows(){
  rowCount=0;
  if (rulesType == PRIVACY_RULES_TYPE_FORWARDS) {
    messageRow=rowCount++;
  }
 else {
    messageRow=-1;
  }
  sectionRow=rowCount++;
  everybodyRow=rowCount++;
  myContactsRow=rowCount++;
  if (rulesType != PRIVACY_RULES_TYPE_LASTSEEN && rulesType != PRIVACY_RULES_TYPE_CALLS && rulesType != PRIVACY_RULES_TYPE_P2P && rulesType != PRIVACY_RULES_TYPE_FORWARDS && rulesType != PRIVACY_RULES_TYPE_PHONE) {
    nobodyRow=-1;
  }
 else {
    nobodyRow=rowCount++;
  }
  detailRow=rowCount++;
  shareSectionRow=rowCount++;
  if (currentType == 1 || currentType == 2) {
    alwaysShareRow=rowCount++;
  }
 else {
    alwaysShareRow=-1;
  }
  if (currentType == 0 || currentType == 2) {
    neverShareRow=rowCount++;
  }
 else {
    neverShareRow=-1;
  }
  shareDetailRow=rowCount++;
  if (rulesType == PRIVACY_RULES_TYPE_CALLS) {
    p2pSectionRow=rowCount++;
    p2pRow=rowCount++;
    p2pDetailRow=rowCount++;
  }
 else {
    p2pSectionRow=-1;
    p2pRow=-1;
    p2pDetailRow=-1;
  }
  setMessageText();
  if (listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
}
