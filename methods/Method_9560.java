private void setMessageText(){
  if (messageCell != null) {
    if (currentType == 0) {
      messageCell.hintView.setOverrideText(LocaleController.getString("PrivacyForwardsEverybody",R.string.PrivacyForwardsEverybody));
      messageCell.messageObject.messageOwner.fwd_from.from_id=1;
    }
 else     if (currentType == 1) {
      messageCell.hintView.setOverrideText(LocaleController.getString("PrivacyForwardsNobody",R.string.PrivacyForwardsNobody));
      messageCell.messageObject.messageOwner.fwd_from.from_id=0;
    }
 else {
      messageCell.hintView.setOverrideText(LocaleController.getString("PrivacyForwardsContacts",R.string.PrivacyForwardsContacts));
      messageCell.messageObject.messageOwner.fwd_from.from_id=1;
    }
    messageCell.cell.forceResetMessageObject();
  }
}
