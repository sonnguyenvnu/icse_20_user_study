@Override protected void onTransitionAnimationStart(boolean isOpen,boolean backward){
  if (!backward && playProfileAnimation && allowProfileAnimation) {
    openAnimationInProgress=true;
  }
  NotificationCenter.getInstance(currentAccount).setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.dialogsNeedReload,NotificationCenter.closeChats,NotificationCenter.mediaCountDidLoad,NotificationCenter.mediaCountsDidLoad});
  NotificationCenter.getInstance(currentAccount).setAnimationInProgress(true);
}
