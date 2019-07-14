private void showMentionDownButton(boolean show,boolean animated){
  if (mentiondownButton == null) {
    return;
  }
  if (show) {
    if (mentiondownButton.getTag() == null) {
      if (mentiondownButtonAnimation != null) {
        mentiondownButtonAnimation.cancel();
        mentiondownButtonAnimation=null;
      }
      if (animated) {
        mentiondownButton.setVisibility(View.VISIBLE);
        mentiondownButton.setTag(1);
        if (pagedownButton.getVisibility() == View.VISIBLE) {
          mentiondownButton.setTranslationY(-AndroidUtilities.dp(72));
          mentiondownButtonAnimation=ObjectAnimator.ofFloat(mentiondownButton,View.ALPHA,0.0f,1.0f).setDuration(200);
        }
 else {
          if (mentiondownButton.getTranslationY() == 0) {
            mentiondownButton.setTranslationY(AndroidUtilities.dp(100));
          }
          mentiondownButtonAnimation=ObjectAnimator.ofFloat(mentiondownButton,View.TRANSLATION_Y,0).setDuration(200);
        }
        mentiondownButtonAnimation.start();
      }
 else {
        mentiondownButton.setVisibility(View.VISIBLE);
      }
    }
  }
 else {
    returnToMessageId=0;
    if (mentiondownButton.getTag() != null) {
      mentiondownButton.setTag(null);
      if (mentiondownButtonAnimation != null) {
        mentiondownButtonAnimation.cancel();
        mentiondownButtonAnimation=null;
      }
      if (animated) {
        if (pagedownButton.getVisibility() == View.VISIBLE) {
          mentiondownButtonAnimation=ObjectAnimator.ofFloat(mentiondownButton,View.ALPHA,1.0f,0.0f).setDuration(200);
        }
 else {
          mentiondownButtonAnimation=ObjectAnimator.ofFloat(mentiondownButton,View.TRANSLATION_Y,AndroidUtilities.dp(100)).setDuration(200);
        }
        mentiondownButtonAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            mentiondownButtonCounter.setVisibility(View.INVISIBLE);
            mentiondownButton.setVisibility(View.INVISIBLE);
          }
        }
);
        mentiondownButtonAnimation.start();
      }
 else {
        mentiondownButton.setVisibility(View.INVISIBLE);
      }
    }
  }
}
