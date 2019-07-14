private void showPagedownButton(boolean show,boolean animated){
  if (pagedownButton == null) {
    return;
  }
  if (show) {
    pagedownButtonShowedByScroll=false;
    if (pagedownButton.getTag() == null) {
      if (pagedownButtonAnimation != null) {
        pagedownButtonAnimation.cancel();
        pagedownButtonAnimation=null;
      }
      if (animated) {
        if (pagedownButton.getTranslationY() == 0) {
          pagedownButton.setTranslationY(AndroidUtilities.dp(100));
        }
        pagedownButton.setVisibility(View.VISIBLE);
        pagedownButton.setTag(1);
        pagedownButtonAnimation=new AnimatorSet();
        if (mentiondownButton.getVisibility() == View.VISIBLE) {
          pagedownButtonAnimation.playTogether(ObjectAnimator.ofFloat(pagedownButton,View.TRANSLATION_Y,0),ObjectAnimator.ofFloat(mentiondownButton,View.TRANSLATION_Y,-AndroidUtilities.dp(72)));
        }
 else {
          pagedownButtonAnimation.playTogether(ObjectAnimator.ofFloat(pagedownButton,View.TRANSLATION_Y,0));
        }
        pagedownButtonAnimation.setDuration(200);
        pagedownButtonAnimation.start();
      }
 else {
        pagedownButton.setVisibility(View.VISIBLE);
      }
    }
  }
 else {
    returnToMessageId=0;
    newUnreadMessageCount=0;
    if (pagedownButton.getTag() != null) {
      pagedownButton.setTag(null);
      if (pagedownButtonAnimation != null) {
        pagedownButtonAnimation.cancel();
        pagedownButtonAnimation=null;
      }
      if (animated) {
        pagedownButtonAnimation=new AnimatorSet();
        if (mentiondownButton.getVisibility() == View.VISIBLE) {
          pagedownButtonAnimation.playTogether(ObjectAnimator.ofFloat(pagedownButton,View.TRANSLATION_Y,AndroidUtilities.dp(100)),ObjectAnimator.ofFloat(mentiondownButton,View.TRANSLATION_Y,0));
        }
 else {
          pagedownButtonAnimation.playTogether(ObjectAnimator.ofFloat(pagedownButton,View.TRANSLATION_Y,AndroidUtilities.dp(100)));
        }
        pagedownButtonAnimation.setDuration(200);
        pagedownButtonAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            pagedownButtonCounter.setVisibility(View.INVISIBLE);
            pagedownButton.setVisibility(View.INVISIBLE);
          }
        }
);
        pagedownButtonAnimation.start();
      }
 else {
        pagedownButton.setVisibility(View.INVISIBLE);
      }
    }
  }
}
