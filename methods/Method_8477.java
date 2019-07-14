private void openKeyboardInternal(){
  showPopup(AndroidUtilities.usingHardwareInput || isPaused ? 0 : 2,0);
  messageEditText.requestFocus();
  AndroidUtilities.showKeyboard(messageEditText);
  if (isPaused) {
    showKeyboardOnResume=true;
  }
 else   if (!AndroidUtilities.usingHardwareInput && !keyboardVisible && !AndroidUtilities.isInMultiwindow) {
    waitingForKeyboardOpen=true;
    AndroidUtilities.cancelRunOnUIThread(openKeyboardRunnable);
    AndroidUtilities.runOnUIThread(openKeyboardRunnable,100);
  }
}
