private void updateMessageListAccessibilityVisibility(){
  if (currentEncryptedChat != null)   return;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    chatListView.setImportantForAccessibility(mentionContainer.getVisibility() == View.VISIBLE || (scrimPopupWindow != null && scrimPopupWindow.isShowing()) ? View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS : View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
  }
}
