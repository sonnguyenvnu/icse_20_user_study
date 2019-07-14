private void updateContactStatus(){
  if (addContactItem == null) {
    return;
  }
  if (currentUser == null) {
    addContactItem.setVisibility(View.GONE);
  }
 else {
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(currentUser.id);
    if (user != null) {
      currentUser=user;
    }
    if (currentEncryptedChat != null && !(currentEncryptedChat instanceof TLRPC.TL_encryptedChat) || MessagesController.isSupportUser(currentUser) || UserObject.isDeleted(currentUser) || ContactsController.getInstance(currentAccount).isLoadingContacts() || (!TextUtils.isEmpty(currentUser.phone) && ContactsController.getInstance(currentAccount).contactsDict.get(currentUser.id) != null && (ContactsController.getInstance(currentAccount).contactsDict.size() != 0 || !ContactsController.getInstance(currentAccount).isLoadingContacts()))) {
      addContactItem.setVisibility(View.GONE);
    }
 else {
      addContactItem.setVisibility(View.VISIBLE);
      if (!TextUtils.isEmpty(currentUser.phone)) {
        addContactItem.setText(LocaleController.getString("AddToContacts",R.string.AddToContacts));
        reportSpamButton.setPadding(AndroidUtilities.dp(4),0,AndroidUtilities.dp(50),0);
        addToContactsButton.setVisibility(View.VISIBLE);
        reportSpamContainer.setLayoutParams(LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,0.5f,Gravity.LEFT | Gravity.TOP,0,0,0,AndroidUtilities.dp(1)));
      }
 else {
        addContactItem.setText(LocaleController.getString("ShareMyContactInfo",R.string.ShareMyContactInfo));
        addToContactsButton.setVisibility(View.GONE);
        reportSpamButton.setPadding(AndroidUtilities.dp(50),0,AndroidUtilities.dp(50),0);
        reportSpamContainer.setLayoutParams(LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,1.0f,Gravity.LEFT | Gravity.TOP,0,0,0,AndroidUtilities.dp(1)));
      }
    }
  }
  checkListViewPaddings();
}
