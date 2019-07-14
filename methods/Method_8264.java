private void updateTitle(){
  if (avatarContainer == null) {
    return;
  }
  if (currentChat != null) {
    avatarContainer.setTitle(currentChat.title,currentChat.scam);
  }
 else   if (currentUser != null) {
    if (currentUser.self) {
      avatarContainer.setTitle(LocaleController.getString("SavedMessages",R.string.SavedMessages));
    }
 else     if (!MessagesController.isSupportUser(currentUser) && ContactsController.getInstance(currentAccount).contactsDict.get(currentUser.id) == null && (ContactsController.getInstance(currentAccount).contactsDict.size() != 0 || !ContactsController.getInstance(currentAccount).isLoadingContacts())) {
      if (!TextUtils.isEmpty(currentUser.phone)) {
        avatarContainer.setTitle(PhoneFormat.getInstance().format("+" + currentUser.phone));
      }
 else {
        avatarContainer.setTitle(UserObject.getUserName(currentUser),currentUser.scam);
      }
    }
 else {
      avatarContainer.setTitle(UserObject.getUserName(currentUser),currentUser.scam);
    }
  }
  setParentActivityTitle(avatarContainer.getTitleTextView().getText());
}
