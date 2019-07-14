private void updateSubtitle(){
  if (actionBar == null || currentMessageObject == null) {
    return;
  }
  if (currentChat != null || currentUser == null) {
    return;
  }
  if (currentUser.id / 1000 != 777 && currentUser.id / 1000 != 333 && ContactsController.getInstance(currentMessageObject.currentAccount).contactsDict.get(currentUser.id) == null && (ContactsController.getInstance(currentMessageObject.currentAccount).contactsDict.size() != 0 || !ContactsController.getInstance(currentMessageObject.currentAccount).isLoadingContacts())) {
    if (currentUser.phone != null && currentUser.phone.length() != 0) {
      nameTextView.setText(PhoneFormat.getInstance().format("+" + currentUser.phone));
    }
 else {
      nameTextView.setText(UserObject.getUserName(currentUser));
    }
  }
 else {
    nameTextView.setText(UserObject.getUserName(currentUser));
  }
  if (currentUser != null && currentUser.id == 777000) {
    onlineTextView.setText(LocaleController.getString("ServiceNotifications",R.string.ServiceNotifications));
  }
 else {
    CharSequence printString=MessagesController.getInstance(currentMessageObject.currentAccount).printingStrings.get(currentMessageObject.getDialogId());
    if (printString == null || printString.length() == 0) {
      lastPrintString=null;
      setTypingAnimation(false);
      TLRPC.User user=MessagesController.getInstance(currentMessageObject.currentAccount).getUser(currentUser.id);
      if (user != null) {
        currentUser=user;
      }
      onlineTextView.setText(LocaleController.formatUserStatus(currentMessageObject.currentAccount,currentUser));
    }
 else {
      lastPrintString=printString;
      onlineTextView.setText(printString);
      setTypingAnimation(true);
    }
  }
}
