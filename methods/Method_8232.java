private void checkContextBotPanel(){
  if (allowStickersPanel && mentionsAdapter != null && mentionsAdapter.isBotContext()) {
    if (!allowContextBotPanel && !allowContextBotPanelSecond) {
      if (mentionContainer.getVisibility() == View.VISIBLE && mentionContainer.getTag() == null) {
        if (mentionListAnimation != null) {
          mentionListAnimation.cancel();
        }
        mentionContainer.setTag(1);
        mentionListAnimation=new AnimatorSet();
        mentionListAnimation.playTogether(ObjectAnimator.ofFloat(mentionContainer,View.ALPHA,0.0f));
        mentionListAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (mentionListAnimation != null && mentionListAnimation.equals(animation)) {
              mentionContainer.setVisibility(View.INVISIBLE);
              mentionListAnimation=null;
              updateMessageListAccessibilityVisibility();
            }
          }
          @Override public void onAnimationCancel(          Animator animation){
            if (mentionListAnimation != null && mentionListAnimation.equals(animation)) {
              mentionListAnimation=null;
            }
          }
        }
);
        mentionListAnimation.setDuration(200);
        mentionListAnimation.start();
      }
    }
 else {
      if (mentionContainer.getVisibility() == View.INVISIBLE || mentionContainer.getTag() != null) {
        if (mentionListAnimation != null) {
          mentionListAnimation.cancel();
        }
        mentionContainer.setTag(null);
        mentionContainer.setVisibility(View.VISIBLE);
        updateMessageListAccessibilityVisibility();
        mentionListAnimation=new AnimatorSet();
        mentionListAnimation.playTogether(ObjectAnimator.ofFloat(mentionContainer,View.ALPHA,0.0f,1.0f));
        mentionListAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (mentionListAnimation != null && mentionListAnimation.equals(animation)) {
              mentionListAnimation=null;
            }
          }
          @Override public void onAnimationCancel(          Animator animation){
            if (mentionListAnimation != null && mentionListAnimation.equals(animation)) {
              mentionListAnimation=null;
            }
          }
        }
);
        mentionListAnimation.setDuration(200);
        mentionListAnimation.start();
      }
    }
  }
}
