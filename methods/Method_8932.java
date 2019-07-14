private void openKeyboardInternal(){
  showPopup(AndroidUtilities.usingHardwareInput ? 0 : 2);
  openKeyboard();
}
