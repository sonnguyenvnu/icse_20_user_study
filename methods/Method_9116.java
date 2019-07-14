private void checkUpdate(){
  if (started) {
    if (!NotificationCenter.getInstance(currentAccount).isAnimationInProgress()) {
      update();
    }
 else {
      AndroidUtilities.runOnUIThread(this::checkUpdate,100);
    }
  }
}
