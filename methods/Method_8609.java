public void hidePopup(boolean byBackButton){
  if (isPopupShowing()) {
    showPopup(0);
  }
  if (byBackButton) {
    hideEmojiView();
  }
}
