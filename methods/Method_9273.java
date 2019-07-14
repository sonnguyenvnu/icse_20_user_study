@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.suggestedLangpack) {
    checkContinueText();
  }
}
