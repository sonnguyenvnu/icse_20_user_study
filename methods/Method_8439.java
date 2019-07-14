private void setRecordVideoButtonVisible(boolean visible,boolean animated){
  if (videoSendButton == null) {
    return;
  }
  videoSendButton.setTag(visible ? 1 : null);
  if (audioVideoButtonAnimation != null) {
    audioVideoButtonAnimation.cancel();
    audioVideoButtonAnimation=null;
  }
  if (animated) {
    SharedPreferences preferences=MessagesController.getGlobalMainSettings();
    boolean isChannel=false;
    if ((int)dialog_id < 0) {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-(int)dialog_id);
      isChannel=ChatObject.isChannel(chat) && !chat.megagroup;
    }
    preferences.edit().putBoolean(isChannel ? "currentModeVideoChannel" : "currentModeVideo",visible).commit();
    audioVideoButtonAnimation=new AnimatorSet();
    audioVideoButtonAnimation.playTogether(ObjectAnimator.ofFloat(videoSendButton,View.SCALE_X,visible ? 1.0f : 0.1f),ObjectAnimator.ofFloat(videoSendButton,View.SCALE_Y,visible ? 1.0f : 0.1f),ObjectAnimator.ofFloat(videoSendButton,View.ALPHA,visible ? 1.0f : 0.0f),ObjectAnimator.ofFloat(audioSendButton,View.SCALE_X,visible ? 0.1f : 1.0f),ObjectAnimator.ofFloat(audioSendButton,View.SCALE_Y,visible ? 0.1f : 1.0f),ObjectAnimator.ofFloat(audioSendButton,View.ALPHA,visible ? 0.0f : 1.0f));
    audioVideoButtonAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (animation.equals(audioVideoButtonAnimation)) {
          audioVideoButtonAnimation=null;
        }
        (videoSendButton.getTag() == null ? audioSendButton : videoSendButton).sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
      }
    }
);
    audioVideoButtonAnimation.setInterpolator(new DecelerateInterpolator());
    audioVideoButtonAnimation.setDuration(150);
    audioVideoButtonAnimation.start();
  }
 else {
    videoSendButton.setScaleX(visible ? 1.0f : 0.1f);
    videoSendButton.setScaleY(visible ? 1.0f : 0.1f);
    videoSendButton.setAlpha(visible ? 1.0f : 0.0f);
    audioSendButton.setScaleX(visible ? 0.1f : 1.0f);
    audioSendButton.setScaleY(visible ? 0.1f : 1.0f);
    audioSendButton.setAlpha(visible ? 0.0f : 1.0f);
  }
}
