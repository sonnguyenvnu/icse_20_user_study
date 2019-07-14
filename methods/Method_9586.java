@Override protected void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen && !backward && playProfileAnimation && allowProfileAnimation) {
    openAnimationInProgress=false;
    if (recreateMenuAfterAnimation) {
      createActionBarMenu();
    }
  }
  NotificationCenter.getInstance(currentAccount).setAnimationInProgress(false);
}
