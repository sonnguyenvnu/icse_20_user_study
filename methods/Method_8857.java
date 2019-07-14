private void processDone(boolean fingerprint){
  if (!fingerprint) {
    if (SharedConfig.passcodeRetryInMs > 0) {
      return;
    }
    String password="";
    if (SharedConfig.passcodeType == 0) {
      password=passwordEditText2.getString();
    }
 else     if (SharedConfig.passcodeType == 1) {
      password=passwordEditText.getText().toString();
    }
    if (password.length() == 0) {
      onPasscodeError();
      return;
    }
    if (!SharedConfig.checkPasscode(password)) {
      SharedConfig.increaseBadPasscodeTries();
      if (SharedConfig.passcodeRetryInMs > 0) {
        checkRetryTextView();
      }
      passwordEditText.setText("");
      passwordEditText2.eraseAllCharacters(true);
      onPasscodeError();
      return;
    }
  }
  SharedConfig.badPasscodeTries=0;
  passwordEditText.clearFocus();
  AndroidUtilities.hideKeyboard(passwordEditText);
  AnimatorSet AnimatorSet=new AnimatorSet();
  AnimatorSet.setDuration(200);
  AnimatorSet.playTogether(ObjectAnimator.ofFloat(this,"translationY",AndroidUtilities.dp(20)),ObjectAnimator.ofFloat(this,"alpha",AndroidUtilities.dp(0.0f)));
  AnimatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      setVisibility(View.GONE);
    }
  }
);
  AnimatorSet.start();
  SharedConfig.appLocked=false;
  SharedConfig.saveConfig();
  NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetPasscode);
  setOnTouchListener(null);
  if (delegate != null) {
    delegate.didAcceptedPassword();
  }
}
