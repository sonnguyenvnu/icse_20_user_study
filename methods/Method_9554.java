private void setTypingAnimation(boolean start){
  if (actionBar == null) {
    return;
  }
  if (start) {
    try {
      Integer type=MessagesController.getInstance(currentMessageObject.currentAccount).printingStringsTypes.get(currentMessageObject.getDialogId());
      onlineTextView.setCompoundDrawablesWithIntrinsicBounds(statusDrawables[type],null,null,null);
      onlineTextView.setCompoundDrawablePadding(AndroidUtilities.dp(4));
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
    onlineTextView.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
    onlineTextView.setCompoundDrawablePadding(0);
    for (int a=0; a < statusDrawables.length; a++) {
      statusDrawables[a].stop();
    }
  }
}
