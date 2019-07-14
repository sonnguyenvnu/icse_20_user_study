public boolean isPopupShowing(){
  return emojiViewVisible || botKeyboardView != null && botKeyboardView.getVisibility() == VISIBLE;
}
