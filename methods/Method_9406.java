@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (presentAfterAnimation != null) {
    AndroidUtilities.runOnUIThread(() -> {
      presentFragment(presentAfterAnimation,true);
      presentAfterAnimation=null;
    }
);
  }
  if (currentActivityType == TYPE_PASSWORD) {
    if (isOpen) {
      if (inputFieldContainers[FIELD_PASSWORD].getVisibility() == View.VISIBLE) {
        inputFields[FIELD_PASSWORD].requestFocus();
        AndroidUtilities.showKeyboard(inputFields[FIELD_PASSWORD]);
      }
      if (usingSavedPassword == 2) {
        onPasswordDone(false);
      }
    }
  }
 else   if (currentActivityType == TYPE_PHONE_VERIFICATION) {
    if (isOpen) {
      views[currentViewNum].onShow();
    }
  }
 else   if (currentActivityType == TYPE_EMAIL) {
    if (isOpen) {
      inputFields[FIELD_EMAIL].requestFocus();
      AndroidUtilities.showKeyboard(inputFields[FIELD_EMAIL]);
    }
  }
 else   if (currentActivityType == TYPE_EMAIL_VERIFICATION) {
    if (isOpen) {
      inputFields[FIELD_EMAIL].requestFocus();
      AndroidUtilities.showKeyboard(inputFields[FIELD_EMAIL]);
    }
  }
 else   if (currentActivityType == TYPE_ADDRESS || currentActivityType == TYPE_IDENTITY) {
    if (Build.VERSION.SDK_INT >= 21) {
      createChatAttachView();
    }
  }
}
