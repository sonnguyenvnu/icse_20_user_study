private void hidePinnedMessageView(boolean animated){
  if (pinnedMessageView.getTag() == null) {
    pinnedMessageView.setTag(1);
    if (pinnedMessageViewAnimator != null) {
      pinnedMessageViewAnimator.cancel();
      pinnedMessageViewAnimator=null;
    }
    if (animated) {
      pinnedMessageViewAnimator=new AnimatorSet();
      pinnedMessageViewAnimator.playTogether(ObjectAnimator.ofFloat(pinnedMessageView,View.TRANSLATION_Y,-AndroidUtilities.dp(50)));
      pinnedMessageViewAnimator.setDuration(200);
      pinnedMessageViewAnimator.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (pinnedMessageViewAnimator != null && pinnedMessageViewAnimator.equals(animation)) {
            pinnedMessageView.setVisibility(View.GONE);
            pinnedMessageViewAnimator=null;
          }
        }
        @Override public void onAnimationCancel(        Animator animation){
          if (pinnedMessageViewAnimator != null && pinnedMessageViewAnimator.equals(animation)) {
            pinnedMessageViewAnimator=null;
          }
        }
      }
);
      pinnedMessageViewAnimator.start();
    }
 else {
      pinnedMessageView.setTranslationY(-AndroidUtilities.dp(50));
      pinnedMessageView.setVisibility(View.GONE);
    }
  }
}
