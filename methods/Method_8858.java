private void checkRetryTextView(){
  long currentTime=SystemClock.elapsedRealtime();
  if (currentTime > SharedConfig.lastUptimeMillis) {
    SharedConfig.passcodeRetryInMs-=(currentTime - SharedConfig.lastUptimeMillis);
    if (SharedConfig.passcodeRetryInMs < 0) {
      SharedConfig.passcodeRetryInMs=0;
    }
  }
  SharedConfig.lastUptimeMillis=currentTime;
  SharedConfig.saveConfig();
  if (SharedConfig.passcodeRetryInMs > 0) {
    int value=Math.max(1,(int)Math.ceil(SharedConfig.passcodeRetryInMs / 1000.0));
    if (value != lastValue) {
      retryTextView.setText(LocaleController.formatString("TooManyTries",R.string.TooManyTries,LocaleController.formatPluralString("Seconds",value)));
      lastValue=value;
    }
    if (retryTextView.getVisibility() != VISIBLE) {
      retryTextView.setVisibility(VISIBLE);
      passwordFrameLayout.setVisibility(INVISIBLE);
      if (numbersFrameLayout.getVisibility() == VISIBLE) {
        numbersFrameLayout.setVisibility(INVISIBLE);
      }
      AndroidUtilities.hideKeyboard(passwordEditText);
      AndroidUtilities.cancelRunOnUIThread(checkRunnable);
      AndroidUtilities.runOnUIThread(checkRunnable,100);
    }
  }
 else {
    AndroidUtilities.cancelRunOnUIThread(checkRunnable);
    if (passwordFrameLayout.getVisibility() != VISIBLE) {
      retryTextView.setVisibility(INVISIBLE);
      passwordFrameLayout.setVisibility(VISIBLE);
      if (SharedConfig.passcodeType == 0) {
        numbersFrameLayout.setVisibility(VISIBLE);
      }
 else       if (SharedConfig.passcodeType == 1) {
        AndroidUtilities.showKeyboard(passwordEditText);
      }
    }
  }
}
