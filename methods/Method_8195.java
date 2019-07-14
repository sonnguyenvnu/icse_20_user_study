@Override public void onTransitionAnimationStart(boolean isOpen,boolean backward){
  NotificationCenter.getInstance(currentAccount).setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.chatInfoDidLoad,NotificationCenter.dialogsNeedReload,NotificationCenter.closeChats,NotificationCenter.messagesDidLoad,NotificationCenter.botKeyboardDidLoad});
  NotificationCenter.getInstance(currentAccount).setAnimationInProgress(true);
  if (isOpen) {
    openAnimationEnded=false;
  }
}
