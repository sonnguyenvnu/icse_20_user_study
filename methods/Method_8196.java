@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  NotificationCenter.getInstance(currentAccount).setAnimationInProgress(false);
  if (isOpen) {
    openAnimationEnded=true;
  }
}
