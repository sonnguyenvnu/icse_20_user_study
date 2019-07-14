public void onResume(){
  checkRetryTextView();
  if (retryTextView.getVisibility() != VISIBLE) {
    if (SharedConfig.passcodeType == 1) {
      if (passwordEditText != null) {
        passwordEditText.requestFocus();
        AndroidUtilities.showKeyboard(passwordEditText);
      }
      AndroidUtilities.runOnUIThread(() -> {
        if (retryTextView.getVisibility() != VISIBLE && passwordEditText != null) {
          passwordEditText.requestFocus();
          AndroidUtilities.showKeyboard(passwordEditText);
        }
      }
,200);
    }
    checkFingerprint();
  }
}
