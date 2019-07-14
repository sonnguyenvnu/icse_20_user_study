@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen) {
    firstNameField.requestFocus();
    AndroidUtilities.showKeyboard(firstNameField);
  }
}
