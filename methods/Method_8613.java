private void openKeyboardInternal(){
  showPopup(AndroidUtilities.usingHardwareInput || isPaused ? 0 : 2);
  editText.requestFocus();
  AndroidUtilities.showKeyboard(editText);
  if (isPaused) {
    showKeyboardOnResume=true;
  }
 else   if (!AndroidUtilities.usingHardwareInput && !keyboardVisible && !AndroidUtilities.isInMultiwindow && !AndroidUtilities.isTablet()) {
    waitingForKeyboardOpen=true;
    AndroidUtilities.cancelRunOnUIThread(openKeyboardRunnable);
    AndroidUtilities.runOnUIThread(openKeyboardRunnable,100);
  }
}
