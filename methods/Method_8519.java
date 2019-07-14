private void setTypingAnimation(boolean start){
  if (start) {
    try {
      Integer type=MessagesController.getInstance(currentAccount).printingStringsTypes.get(parentFragment.getDialogId());
      subtitleTextView.setLeftDrawable(statusDrawables[type]);
      for (int a=0; a < statusDrawables.length; a++) {
        if (a == type) {
          statusDrawables[a].start();
        }
 else {
          statusDrawables[a].stop();
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
 else {
    subtitleTextView.setLeftDrawable(null);
    for (int a=0; a < statusDrawables.length; a++) {
      statusDrawables[a].stop();
    }
  }
}
