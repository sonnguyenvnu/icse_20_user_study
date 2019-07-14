@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  NotificationCenter.getInstance(currentAccount).setAnimationInProgress(false);
  if (isOpen) {
    openAnimationEnded=true;
    if (Build.VERSION.SDK_INT >= 21) {
      createChatAttachView();
    }
    if (chatActivityEnterView.hasRecordVideo() && !chatActivityEnterView.isSendButtonVisible()) {
      boolean isChannel=false;
      if (currentChat != null) {
        isChannel=ChatObject.isChannel(currentChat) && !currentChat.megagroup;
      }
      SharedPreferences preferences=MessagesController.getGlobalMainSettings();
      String key=isChannel ? "needShowRoundHintChannel" : "needShowRoundHint";
      if (preferences.getBoolean(key,true)) {
        if (Utilities.random.nextFloat() < 0.2f) {
          showVoiceHint(false,chatActivityEnterView.isInVideoMode());
          preferences.edit().putBoolean(key,false).commit();
        }
      }
    }
    if (!backward && parentLayout != null) {
      for (int a=0, N=parentLayout.fragmentsStack.size() - 1; a < N; a++) {
        BaseFragment fragment=parentLayout.fragmentsStack.get(a);
        if (fragment != this && fragment instanceof ChatActivity) {
          ChatActivity chatActivity=(ChatActivity)fragment;
          if (chatActivity.dialog_id == dialog_id) {
            fragment.removeSelfFromStack();
            break;
          }
        }
      }
    }
  }
}
