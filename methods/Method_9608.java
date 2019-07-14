@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen) {
    AndroidUtilities.runOnUIThread(() -> {
      if (firstNameField != null) {
        firstNameField.requestFocus();
        AndroidUtilities.showKeyboard(firstNameField);
      }
    }
,100);
  }
}
