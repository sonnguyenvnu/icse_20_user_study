@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen) {
    AndroidUtilities.runOnUIThread(() -> {
      if (usernameTextView != null) {
        usernameTextView.requestFocus();
        AndroidUtilities.showKeyboard(usernameTextView);
      }
    }
,100);
  }
}
