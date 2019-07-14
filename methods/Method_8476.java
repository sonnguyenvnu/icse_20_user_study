public void hidePopup(boolean byBackButton){
  if (isPopupShowing()) {
    if (currentPopupContentType == 1 && byBackButton && botButtonsMessageObject != null) {
      SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
      preferences.edit().putInt("hidekeyboard_" + dialog_id,botButtonsMessageObject.getId()).commit();
    }
    if (byBackButton && searchingType != 0) {
      searchingType=0;
      emojiView.closeSearch(true);
      messageEditText.requestFocus();
      setStickersExpanded(false,true,false);
      if (emojiTabOpen) {
        checkSendButton(true);
      }
    }
 else {
      if (searchingType != 0) {
        searchingType=0;
        emojiView.closeSearch(false);
        messageEditText.requestFocus();
      }
      showPopup(0,0);
    }
  }
}
